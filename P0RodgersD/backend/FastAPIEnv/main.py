import sys
import os
from pathlib import Path
from xmlrpc import client

# 1. Get the path to where the 'agents' folder actually lives
# We use Path(__file__) to make it work regardless of where you launch from
current_dir = Path(__file__).parent
carla_root = current_dir / "PythonAPI" / "carla"

# 2. Add it to the system path so Python treats 'carla' as a root folder
sys.path.append(str(carla_root))

from pydantic import BaseModel
from typing import Optional
from csv import reader, writer
import json
import carla
import asyncio
import json
import math
import socket
from PythonAPI.carla.agents.navigation.behavior_agent import BehaviorAgent
from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from fastapi.middleware.cors import CORSMiddleware
import pygame
from pygame.locals import K_w, K_s, K_a, K_d, K_SPACE, K_ESCAPE
import uuid
import logging


# ==========================================
# Setting up your logging
# ==========================================

# Get the uvicorn logger
logger = logging.getLogger("uvicorn")
# Optional: set the level (INFO is standard)
logger.setLevel(logging.INFO)


# ==========================================
# Global variables and app initialization
# ==========================================

# Temporary store for test configs
pending_tests = {}
app = FastAPI()

# Define which "Origins" are allowed to talk to your server
origins = [
    "*"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,            # Only allow your React app
    allow_credentials=True,
    allow_methods=["*"],              # Allow POST, GET, etc.
    allow_headers=["*"],              # Allow Content-Type, Authorization, etc.
)


# ======================================================
# Defining your data types for requests
# ======================================================

class Coordinate(BaseModel):
    x: float
    y: float
    z: float

# Define the structure of the data you want to receive
class CarlaParams(BaseModel):
    port: int = 9000
    town: str = "Town01"
    vehicle_type: str = "vehicle.tesla.model3"
    spawn_point: Coordinate

# ======================================================
# Handling the websocket connections and CARLA interactions
# ======================================================

class ConnectionManager:
    def __init__(self):
        self.active_connections: dict[str, WebSocket] = {}

    async def connect(self, test_id: str, websocket: WebSocket):
        await websocket.accept()
        logger.info(f"WebSocket connection accepted for test_id: {test_id}")
        self.active_connections[test_id] = websocket

    async def disconnect(self, test_id: str):
        """
        Gently shuts down the socket and removes it from the registry.
        """
        # 1. Pull the socket out of our registry
        websocket = self.active_connections.pop(test_id, None)
        
        # 2. If it exists, try to physically close the connection
        if websocket:
            try:
                # We check the state to avoid 'Already Closed' errors
                # code 1000 = Normal Closure
                await websocket.close(code=1000)
                print(f"Successfully closed socket for {test_id}")
            except Exception as e:
                # If the browser already killed the connection, this might fail,
                # which is fine—we just want to make sure the resource is released.
                print(f"Error during socket closure for {test_id}: {e}")

    async def send_message(self, test_id: str, message: dict):
        websocket = self.active_connections.get(test_id)
        if websocket:
            try:
                await websocket.send_json(message)
            except Exception:
                # If sending fails, the socket is likely dead. 
                # We should trigger a disconnect to clean up our list.
                await self.disconnect(test_id)

sockManager = ConnectionManager()

# ======================================================
# Define your API endpoints
# ======================================================
@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.get("/items/{item_id}")
def read_item(item_id: int, q: str | None = None):
    return {"item_id": item_id, "q": q}

@app.post("/carla-testing")
async def async_carla_test(carla_params: CarlaParams):
    # 1. Generate a unique ID for this specific test run
    test_id = str(uuid.uuid4())

    logger.info(f"Received test initialization request: {carla_params}")
    
    # 2. Store the params so the WebSocket can find them later
    pending_tests[test_id] = carla_params
    
    # 3. Return the ID and the URL for the frontend to connect to
    logger.info(f"Test {test_id} initialized with params: {carla_params}")
    return {
        "test_id": test_id, 
        "ws_url": f"ws://localhost:8081/ws/carla/{test_id}"
    }

@app.websocket("/ws/carla/{test_id}")
async def carla_ws_run(websocket: WebSocket, test_id: str):
    
    # await websocket.accept()
    await sockManager.connect(test_id, websocket)
    
    # 4. Pull the config we saved in the POST request
    # Retrieve the test parameters
    carla_params = pending_tests.get(test_id)
    if not carla_params:
        #await websocket.close(code=1008)
        await sockManager.disconnect(test_id)
        return

    # Access your data using params.field_name
    print(f"Spawning a {carla_params.vehicle_type} at point {carla_params.spawn_point}")

    # Step 1: Initalize Pygame and CARLA client
    # --- Initialize Pygame for keyboard input ---
    pygame.init()
    screen = pygame.display.set_mode((400, 300))
    pygame.display.set_caption("Carla Bike Control")
    logger.info("Pygame initialized and window created.")

    try:
        # ========= CARLA SETUP =========
        client = carla.Client("localhost", carla_params.port)
        client.set_timeout(50.0)
        logger.info(f"Connected to CARLA on port {carla_params.port}")
        logger.info(f"CARLA client initialized with world {client.get_world().get_map().name}")
        # Quick test of the socket
        await websocket.send_json({"message": "CARLA setup started"})
        # ========= Step 2: Load the world with the configurations from the request ===========

        # Load town and get world object
        await client.load_world(carla_params.town)
        world = await client.get_world()
        logger.info(f"Loaded world: {carla_params.town}")

        # Setting up the spectator
        spectator = await world.get_spectator()
        logger.info("Spectator set up.")

        # ======== Step 2.5: Getting the parameters ready for carla ==========
        
        # Location/set
        new_loc = carla.Location(
            x=carla_params.spawn_point.x,
            y=carla_params.spawn_point.y,
            z=carla_params.spawn_point.z
        )

        # Spectator/set
        new_rot = carla.Rotation(pitch=0, yaw=0, roll=0)
        await spectator.set_transform(carla.Transform(new_loc, new_rot))

        # Vehicle/set
        vehicle_bp = world.get_blueprint_library().find(carla_params.vehicle_type)

        # Attach collision sensor 
        collision_bp = world.get_blueprint_library().find('sensor.other.collision')
        collision_transform = await carla.Transform(vehicle.get_transform().location)
        collision_sensor = world.spawn_actor(collision_bp, collision_transform, attach_to=vehicle)

        # ========== Step 3: Spawn the vehicle at the specified spawn point ==========
        spawn_point = carla.Transform(new_loc, new_rot)
        vehicle = world.spawn_actor(vehicle_bp, spawn_point)
        logger.info("Vehicle spawned.")

        # ========== Step 4: Set up actions on events ==========
        collision_sensor.listen(on_collision, websocket, client)
    except Exception as e:
        logger.error(f"Error during CARLA setup: {e}")
        await sockManager.disconnect(test_id)
        return
    

    # Main control loop
    try:
        control = carla.VehicleControl()
        clock = pygame.time.Clock()
        
        running = True
        while running:
            clock.tick(30)  # 30 FPS
            
            # Reset controls each frame
            control.throttle = 0.0
            control.brake = 0.0
            control.steer = 0.0
            control.hand_brake = False
            
            # Check pressed keys
            keys = pygame.key.get_pressed()
            if keys[K_w]:
                control.throttle = 1
            if keys[K_s]:
                control.brake = 0.5
            if keys[K_a]:
                control.steer = -0.5
            if keys[K_d]:
                control.steer = 0.5
            if keys[K_SPACE]:
                control.hand_brake = True
            
            vehicle.apply_control(control)

            #Build json to send over
            data = {
                "type": "telemetry",
                "town": carla_params.town,
                "throttle": control.throttle,
                "steer": control.steer,
                "brake": control.brake,
                "speed": vehicle.get_velocity().length(),
                "location": {
                    "x": vehicle.get_location().x,
                    "y": vehicle.get_location().y,
                    "z": vehicle.get_location().z
                }
            }

            #print("Current location: ", spectator.location)

            if collision_data is not None:
                await websocket.send_json(collision_data)
                logger.info(f"Sent collision: {collision_data}")
                collision_data = None
            
            # Send data across socket 
            await websocket.send_json(data)
            await asyncio.sleep(0.01)
            
            # Exit on ESC
            for event in pygame.event.get():
                if event.type == pygame.QUIT or keys[K_ESCAPE]:
                    running = False
                    await websocket.close()
                    logger.info("Simulation ended by user.")
    # Clean up
    finally:
        vehicle.destroy()
        pygame.quit()
        print("Vehicle destroyed, script ended.")
        websocket.close()



# 4. The WebSocket Execution Loop
# Ai defined way to make the socket. Horrible, will do it myself

# @app.websocket("/ws/carla/{test_id}")
# async def carla_ws_run(websocket: WebSocket, test_id: str):
#     await websocket.accept()
    
#     # Retrieve the configuration
#     params = pending_tests.get(test_id)
#     if not params:
#         await websocket.close(code=1008)
#         return

#     # --- Setup CARLA & Pygame ---
#     pygame.init()
#     screen = pygame.display.set_mode((400, 300))
    
#     client = carla.Client("localhost", params.port)
#     client.set_timeout(10.0)
    
#     world = client.load_world(params.town)
#     blueprint_library = world.get_blueprint_library()
    
#     # Spawn Setup
#     vehicle_bp = blueprint_library.find(params.vehicle_type)
#     spawn_transform = carla.Transform(
#         carla.Location(x=params.spawn_point.x, y=params.spawn_point.y, z=params.spawn_point.z),
#         carla.Rotation(pitch=0, yaw=0, roll=0)
#     )

#     actor_list = []
    
#     try:
#         # Spawn Vehicle
#         vehicle = world.spawn_actor(vehicle_bp, spawn_transform)
#         actor_list.append(vehicle)

#         # Spawn Sensor
#         collision_bp = blueprint_library.find('sensor.other.collision')
#         collision_sensor = world.spawn_actor(collision_bp, carla.Transform(), attach_to=vehicle)
#         actor_list.append(collision_sensor)

#         # Thread-safe collision flag
#         collision_event_data = None
#         def on_collision(event):
#             nonlocal collision_event_data
#             collision_event_data = {"type": "collision", "impulse": event.normal_impulse.length()}

#         collision_sensor.listen(on_collision)
        
#         control = carla.VehicleControl()
#         clock = pygame.time.Clock()

#         # --- MAIN SIMULATION LOOP ---
#         while True:
#             # HEARTBEAT: Check if client is still connected
#             try:
#                 await asyncio.wait_for(websocket.receive_text(), timeout=0.0001)
#             except asyncio.TimeoutError:
#                 pass 

#             clock.tick(30)
            
#             # --- PYGAME EVENT HANDLING ---
#             # This captures the 'X' button on the window or system-level quits
#             for event in pygame.event.get():
#                 if event.type == pygame.QUIT:
#                     print("Pygame window closed. Ending simulation.")
#                     break # This breaks the 'for' loop
#             else:
#                 # This 'else' belongs to the 'for' loop. 
#                 # If the 'for' loop finishes WITHOUT hitting 'break' (Pygame is still running),
#                 # continue with the rest of the simulation.
                
#                 keys = pygame.key.get_pressed()
                
#                 # Exit on ESC key
#                 if keys[K_ESCAPE]:
#                     break

#                 # Apply controls
#                 control.throttle = 1.0 if keys[K_w] else 0.0
#                 control.brake = 1.0 if keys[K_s] else 0.0
#                 control.steer = -0.5 if keys[K_a] else (0.5 if keys[K_d] else 0.0)
#                 control.hand_brake = keys[K_SPACE]
#                 vehicle.apply_control(control)

#                 # Telemetry Prep
#                 loc = vehicle.get_location()
#                 telemetry = {
#                     "type": "telemetry",
#                     "speed": vehicle.get_velocity().length(),
#                     "location": {"x": loc.x, "y": loc.y, "z": loc.z}
#                 }

#                 # Send Data to React
#                 await websocket.send_json(telemetry)

#                 # Send Collision if occurred
#                 if collision_event_data:
#                     await websocket.send_json(collision_event_data)
#                     collision_event_data = None

#                 await asyncio.sleep(0.01)
#                 continue # Skip the outer break

#             break # Only reached if the 'for' loop was broken (pygame.QUIT)
    
#     except WebSocketDisconnect:
#         print(f"Client {test_id} disconnected. Terminating simulation.", flush=True)
#     except Exception as e:
#         print(f"Simulation Error: {e}", flush=True)
#     finally:
#         # --- CLEANUP: This MUST run to allow the next button click to work ---
#         print("Cleaning up actors and closing resources...", flush=True)
#         for actor in actor_list:
#             if actor is not None and actor.is_alive:
#                 actor.destroy()
        
#         pygame.quit()
#         pending_tests.pop(test_id, None)
        
#         # Ensure the socket is formally closed
#         try:
#             await websocket.close()
#         except:
#             pass

# ======================================================
# We are adding util functions that we can use here to interact with CARLA. This is where we will put all of our CARLA related code, and then we can call these functions from our API endpoints.
# ======================================================


async def on_collision(event, websocket: WebSocket, client: carla.Client):
    impulse = event.normal_impulse
    force = math.sqrt(impulse.x**2 + impulse.y**2 + impulse.z**2)

    collision_data = {
        "event": "collision",
        "force": force,
        "other_actor": event.other_actor.type_id,
        "timestamp": client.get_world().get_snapshot().timestamp.elapsed_seconds
    }

    await websocket.send_json(collision_data)
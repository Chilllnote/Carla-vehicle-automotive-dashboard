import carla
import time

def main():
    client = carla.Client('127.0.0.1', 2000)
    client.set_timeout(10.0)
    world = client.get_world()

    # Find a vehicle
    blueprint_library = world.get_blueprint_library()
    # Using a standard sedan for better compatibility
    bp = blueprint_library.find('vehicle.tesla.model3') 
    
    # We set 'hero' for Scenario Runner and 'ego_vehicle' for some manual_control versions
    bp.set_attribute('role_name', 'hero')
    bp.set_attribute('number_of_wheels', '4')

    spawn_point = world.get_map().get_spawn_points()[0]
    vehicle = world.spawn_actor(bp, spawn_point)
    
    # Optional: Enable autopilot so it moves even if manual_control fails
    vehicle.set_autopilot(True)

    print(f"Ego Vehicle Spawned! ID: {vehicle.id}")
    spectator = world.get_spectator()

    try:
        while True:
            # Continuously snap spectator to follow the car
            v_transform = vehicle.get_transform()
            # Position camera behind and above
            look_at = carla.Transform(v_transform.location + v_transform.get_forward_vector() * -10 + carla.Location(z=5),
                                     v_transform.rotation)
            spectator.set_transform(look_at)
            time.sleep(0.02) # ~50 FPS
    except KeyboardInterrupt:
        vehicle.destroy()
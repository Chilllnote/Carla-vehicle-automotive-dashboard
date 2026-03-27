package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.example.Postgres_Objects.*;

import org.json.JSONObject;

import com.example.JSON_classes.configJson;

public class AsyncSocketReceiver extends Application {

    private volatile Telemetry telemetry = null;
    private volatile collisionObj collision = null;
    private boolean quit = false;

    public void startReceiver(int port) {

        PostgresRepository repo = new PostgresRepository();

        launch();
        new Thread(() -> {

            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Waiting for a connection...");

                Socket client = serverSocket.accept();
                System.out.println("Client connected!");

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(client.getOutputStream()));

                // -------------------------------------------------
                // MAIN EXCHANGE LOOP — can be modified freely later
                // -------------------------------------------------

                // Build a single test message

                Route chosenRoute = repo.readRoute(1);
                Vehicle vehicle = repo.readVehicle(1);
                JSONObject json = new JSONObject(
                        new configJson(
                                new Location(chosenRoute.getStartLocation().getX(),
                                        chosenRoute.getStartLocation().getY(), chosenRoute.getStartLocation().getZ()),
                                chosenRoute.getTown(),
                                vehicle.getMake(),
                                vehicle.getModel()).toJson());

                // Scanner scanin = new Scanner(System.in);
                // json.put("command", scanin.nextLine());

                // Send to Python
                writer.write(json.toString() + "\n");
                writer.flush();

                // Read from Python
                // String response = reader.readLine();
                // System.out.println("Received from Python: " + response);

                // After breaking out of while loop → new code here
                System.out.println("Configuration phase complete. Moving on...");
                // You can now read telemetry, collisions, etc.

                // ---------------------------------------------
                // TELEMETRY BLOCK GOES HERE
                // (your commented code)

                String line;
                while ((line = reader.readLine()) != null) {
                    JSONObject obj = new JSONObject(line);

                    System.out.println(obj.toString());

                    if ("collision".equals(obj.getString("type"))) {
                        // Handle collision event if needed

                        this.collision = new collisionObj(
                                obj.getString("type"),
                                obj.getString("actor"),
                                new java.util.Date(obj.getLong("time")),
                                obj.getDouble("impact_force"));

                    } else {

                        this.telemetry = new Telemetry(
                                obj.getString("type"),
                                obj.getDouble("throttle"),
                                obj.getDouble("steer"),
                                obj.getDouble("brake"),
                                obj.getDouble("speed"),
                                obj.getJSONObject("location").getDouble("x"),
                                obj.getJSONObject("location").getDouble("y"),
                                obj.getJSONObject("location").getDouble("z"),
                                obj.getString("town"));
                    }
                }
                // ---------------------------------------------

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Login Page");
            stage.setScene(new Scene(root, 600, 800));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // block for configuration

    public Telemetry getTelemetry() {
        return this.telemetry;
    }

    public collisionObj getCollision() {
        collisionObj temp = this.collision;
        this.collision = null; // Reset after fetching
        return temp;
    }
}

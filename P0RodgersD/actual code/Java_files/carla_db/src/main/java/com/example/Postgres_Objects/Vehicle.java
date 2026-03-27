package com.example.Postgres_Objects;

public class Vehicle {

    private int vehicleID;
    private String make;
    private String model;

    // Constructors
    public Vehicle() {
    }

    public Vehicle(int vehicleID, String make, String model) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
    }

    // Getters and Setters
    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
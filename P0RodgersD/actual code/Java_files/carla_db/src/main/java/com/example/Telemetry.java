package com.example;

public class Telemetry {

    private String type;
    private double throttle;
    private double steer;
    private double brake;
    private double speed;
    private String town;
    private double x_location;
    private double y_location;
    private double z_location;

    public Telemetry(String type, double throttle, double steer, double brake, double speed, double x_location,
            double y_location, double z_location, String town) {
        this.type = type;
        this.throttle = throttle;
        this.steer = steer;
        this.brake = brake;
        this.speed = speed;
        this.x_location = x_location;
        this.y_location = y_location;
        this.z_location = z_location;
        this.town = town;
    }

    public String getType() {
        return this.type;
    }

    public double getThrottle() {
        return this.throttle;
    }

    public double getSteer() {
        return this.steer;
    }

    public double getBrake() {
        return this.brake;
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getX_location() {
        return this.x_location;
    }

    public double getY_location() {
        return this.y_location;
    }

    public double getZ_location() {
        return this.z_location;
    }

    public String getTown() {
        return this.town;
    }

}

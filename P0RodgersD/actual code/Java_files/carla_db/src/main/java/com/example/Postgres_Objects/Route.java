package com.example.Postgres_Objects;

public class Route {

    private int routeID;
    private String town;
    private String name;
    private Location startLocation;
    private Location endLocation;
    private double distance;

    // Constructors
    public Route() {
    }

    public Route(int routeID, String town, String name, Location startLocation,
            Location endLocation, double distance) {
        this.routeID = routeID;
        this.town = town;
        this.name = name;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;
    }

    // Getters and Setters
    public int getRouteID() {
        return this.routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getName() {
        return this.name;
    }

    public Location getStartLocation() {
        return this.startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return this.endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeID=" + this.routeID +
                ", town='" + this.town + '\'' +
                ", startLocation='" + this.startLocation + '\'' +
                ", endLocation='" + this.endLocation + '\'' +
                ", distance=" + this.distance +
                '}';
    }
}
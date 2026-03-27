package com.example.Postgres_Objects;

import java.util.*;

import org.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;
import com.fasterxml.jackson.databind.JsonNode;

public class Trip {

    private int tripID;
    private int employeeID;
    private int vehicleID;
    private int routeID;
    private String tripTestName;
    private Date tripDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private String events; // For JSONB type

    // Constructors
    public Trip() {
    }

    public Trip(int tripID, int employeeID, int vehicleID, int routeID, String tripTestName,
            Date tripDate, Timestamp startTime, Timestamp endTime, String events) {
        this.tripID = tripID;
        this.employeeID = employeeID;
        this.vehicleID = vehicleID;
        this.routeID = routeID;
        this.tripTestName = tripTestName;
        this.tripDate = tripDate;
        this.startTime = startTime;
        this.endTime = endTime;
        if (isValidJson(events)) {
            this.events = events;
        } else {
            throw new IllegalArgumentException("Your events object is not a valid JSON object.");
        }

    }

    // Getters and Setters
    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getTripTestName() {
        return this.tripTestName;
    }

    public void setTripTestName(String testName) {
        this.tripTestName = testName;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getEvents() {
        return this.events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripID=" + tripID +
                ", employeeID=" + employeeID +
                ", vehicleID=" + vehicleID +
                ", routeID=" + routeID +
                ", tripDate=" + tripDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", events=" + events +
                '}';
    }

    private boolean isValidJson(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
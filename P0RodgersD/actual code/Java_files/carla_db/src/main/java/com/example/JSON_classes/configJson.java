package com.example.JSON_classes;

import com.example.Postgres_Objects.Location;

// The JSON configuration we send to python for the Carla simulation
public class configJson {

    private Location coordinates;
    private String town;
    private String vehicleMake;
    private String vehicleModel;

    public configJson(Location coordinates, String town, String vehicleMake, String vehicleModel) {
        this.coordinates = coordinates;
        this.town = town;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;

    }

    public String toJson() {
        return String.format(
                "{ \"type\": \"config\", " +
                        "\"coordinates\": %s," +
                        " \"town\": \"%s\", " +
                        " \"Vehicle\": \"vehicle.%s.%s\"}",
                this.coordinates.toString(),
                this.town,
                this.vehicleMake,
                this.vehicleModel);
    }
}

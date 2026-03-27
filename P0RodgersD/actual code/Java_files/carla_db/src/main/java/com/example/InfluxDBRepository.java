package com.example;

import java.time.Instant;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;

import java.util.Map;
import java.util.HashMap;
import com.influxdb.client.DeleteApi;
import com.influxdb.v3.client.InfluxDBClient;
import com.influxdb.v3.client.Point;
import com.influxdb.v3.client.query.QueryOptions;
import com.influxdb.v3.client.write.WriteOptions;

public class InfluxDBRepository {

    private String hostUrl = "https://us-east-1-1.aws.cloud2.influxdata.com";
    private char[] authToken = "inHgdYJMtbQAcLsIS-QtvIcsFeGfw2FKVmhurGfkmB1wM5BUZS3hzwZkdq0Sicyv5QIf2UXtozVHR2VCrxfOtQ=="
            .toCharArray();
    private String influxBucket = "P0_bucket";
    private WriteOptions option;
    private InfluxDBClient client;

    // Set write options for InfluxDB
    WriteOptions options = new WriteOptions.Builder().database(influxBucket).build();

    InfluxDBRepository() {
        this.client = InfluxDBClient.getInstance(this.hostUrl, this.authToken, this.influxBucket);
        this.option = new WriteOptions.Builder().database(influxBucket).build();
    }

    public void createCollision(collisionObj coll, String runID, String town) {
        // Implementation for creating a record in InfluxDB
        System.out.println("Collision detected! Skipping telemetry write.");
        System.out.println("Collision Details - Type: " + coll.getEventType() +
                ", Actor: " + coll.getActor() +
                ", Impact Force: " + coll.getImpactForce());
        Point pointCollision = Point.measurement("Collisions")
                .setTag("Location", town)
                .setTag("Run_ID", runID)
                .setField("event_type", coll.getEventType())
                .setField("actor_involved", coll.getActor())
                .setField("impact_force", coll.getImpactForce())
                .setTimestamp(Instant.now());
        this.client.writePoint(pointCollision, options);
        System.out.println("Writing Collision: " + (coll != null ? coll.getEventType() : "No Collision"));

    }

    public void createTrip(Telemetry t, String runID) {
        // Implementation for creating a collision record in InfluxDB
        Point pointTelemetry = Point.measurement("Vehicle_metrics")
                .setTag("Location", t.getTown())
                .setTag("Run_ID", runID)
                .setField("throttle", t.getThrottle())
                .setField("steer", t.getSteer())
                .setField("brake", t.getBrake())
                .setField("speed", t.getSpeed())
                .setField("x_location", t.getX_location())
                .setField("y_location", t.getY_location())
                .setField("z_location", t.getZ_location())
                .setTimestamp(Instant.now());
        this.client.writePoint(pointTelemetry, options);
        System.out.println("Writing point: " + pointTelemetry.toLineProtocol());

    }

    public void readTrip() {
        // Implementation for reading a record from InfluxDB
    }

    public void updateTrip() {
        // Implementation for updating a record in InfluxDB
    }

    public void deleteTrip(String bucket, String measurement, String tagKey, String tagValue) {
        try {
            String predicate = String.format(
                    "_measurement=\"%s\" AND %s=\"%s\"",
                    measurement, tagKey, tagValue);

            String jsonPayload = String.format(
                    "{\"start\":\"1970-01-01T00:00:00Z\",\"stop\":\"%s\",\"predicate\":\"%s\"}",
                    Instant.now().toString(), predicate);

            String url = "https://us-east-1-1.aws.cloud2.influxdata.com/api/v2/delete?org=" + "Revature" + "&bucket="
                    + bucket;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization",
                    "inHgdYJMtbQAcLsIS-QtvIcsFeGfw2FKVmhurGfkmB1wM5BUZS3hzwZkdq0Sicyv5QIf2UXtozVHR2VCrxfOtQ==");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes());
            }

            if (conn.getResponseCode() == 204) {
                System.out.println("Successfully deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void deleteTrip(String runID, String startDate, String endDate);

    public void deleteCollision(String runID) {
        // Implementation for deleting a collision record from InfluxDB
        String deleteSQL = "DELETE FROM Collisions WHERE Run_ID='" + runID + "'";

        try {
            this.client.query(deleteSQL);
        } catch (Exception e) {
            System.out.println("Error deleting trip records: " + e.getMessage());
            // System.out.println("No collision records to delete.");
            return;
        }
    }

    public Object readRun(String runID) {
        // Implementation for deleting a collision record from InfluxDB
        String readSQL = "SELECT * FROM \"Vehicle_metrics\" WHERE Run_ID='" + runID + "'";

        try {
            return this.client.query(readSQL);
        } catch (Exception e) {
            System.out.println("Error getting Run: " + e.getMessage());

            return null;
        }
    }

    public void loadTrip() {
        // Implementation for loading records from InfluxDB
    }

    public void saveTrip() {
        // Implementation for saving records to InfluxDB
    }
}

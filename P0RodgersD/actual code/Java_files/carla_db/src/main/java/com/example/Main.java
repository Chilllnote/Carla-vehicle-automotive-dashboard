package com.example;

import java.util.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.stream.Stream;

import com.influxdb.v3.client.InfluxDBClient;
import com.influxdb.v3.client.Point;
import com.influxdb.v3.client.query.QueryOptions;
import com.influxdb.v3.client.query.QueryType;
import com.influxdb.v3.client.write.WriteOptions;
import com.example.JSON_classes.configJson;
import com.example.Postgres_Objects.*;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.sql.Date;

import org.json.JSONObject;
import com.influxdb.client.*;
import com.influxdb.client.domain.WritePrecision;

public class Main {

    public static void main(String[] args) throws Exception {

        // Testing out postgres database

        // Parsing an immediate date time

        PostgresRepository repo = new PostgresRepository();

        // -----------EMPLOYEE CREATION----------------

        // repo.createEmployee(new Employee(1, "James", "Sunderland",
        // "SilentHill@gmail.com", "jametown", "wifeguy",
        // "ADMIN", new Timestamp(0)));

        // ------------------------VEHICLE CREATION---------------------

        // repo.createVehicle(new Vehicle(1, "audi", "a2"));
        // repo.createVehicle(new Vehicle(2, "nissan", "micra"));
        // repo.createVehicle(new Vehicle(3, "audi", "tt"));
        // repo.createVehicle(new Vehicle(4, "mercedes", "coupe_2020"));
        // repo.createVehicle(new Vehicle(5, "bmw", "grandtourer"));
        // repo.createVehicle(new Vehicle(6, "harley-davidson", "low_rider"));
        // repo.createVehicle(new Vehicle(7, "ford", "ambulance"));
        // repo.createVehicle(new Vehicle(8, "micro", "microlino"));
        // repo.createVehicle(new Vehicle(9, "carlamotors", "firetruck"));
        // repo.createVehicle(new Vehicle(10, "carlamotors", "carlacola"));
        // repo.createVehicle(new Vehicle(11, "carlamotors", "european_hgv"));
        // repo.createVehicle(new Vehicle(12, "ford", "mustang"));
        // repo.createVehicle(new Vehicle(13, "chevrolet", "impala"));
        // repo.createVehicle(new Vehicle(14, "lincoln", "mkz_2020"));
        // repo.createVehicle(new Vehicle(15, "citroen", "c3"));
        // repo.createVehicle(new Vehicle(16, "dodge", "charger_police"));
        // repo.createVehicle(new Vehicle(17, "nissan", "patrol"));
        // repo.createVehicle(new Vehicle(18, "jeep", "wrangler_rubicon"));
        // repo.createVehicle(new Vehicle(19, "mini", "cooper_s"));
        // repo.createVehicle(new Vehicle(20, "mercedes", "coupe"));
        // repo.createVehicle(new Vehicle(21, "dodge", "charger_2020"));
        // repo.createVehicle(new Vehicle(22, "ford", "crown"));
        // repo.createVehicle(new Vehicle(23, "seat", "leon"));
        // repo.createVehicle(new Vehicle(24, "toyota", "prius"));
        // repo.createVehicle(new Vehicle(25, "yamaha", "yzf"));
        // repo.createVehicle(new Vehicle(26, "kawasaki", "ninja"));
        // repo.createVehicle(new Vehicle(27, "bh", "crossbike"));
        // repo.createVehicle(new Vehicle(28, "mitsubishi", "fusorosa"));
        // repo.createVehicle(new Vehicle(29, "tesla", "model3"));
        // repo.createVehicle(new Vehicle(30, "gazelle", "omafiets"));
        // repo.createVehicle(new Vehicle(31, "tesla", "cybertruck"));
        // repo.createVehicle(new Vehicle(32, "diamondback", "century"));
        // repo.createVehicle(new Vehicle(33, "mercedes", "sprinter"));
        // repo.createVehicle(new Vehicle(34, "audi", "etron"));
        // repo.createVehicle(new Vehicle(35, "volkswagen", "t2"));
        // repo.createVehicle(new Vehicle(36, "lincoln", "mkz_2017"));
        // repo.createVehicle(new Vehicle(37, "dodge", "charger_police_2020"));
        // repo.createVehicle(new Vehicle(38, "vespa", "zx125"));
        // repo.createVehicle(new Vehicle(39, "mini", "cooper_s_2021"));
        // repo.createVehicle(new Vehicle(40, "nissan", "patrol_2021"));
        // repo.createVehicle(new Vehicle(41, "volkswagen", "t2_2021"));

        // -------------------------ROUTE CREATION----------------------

        // Location start1 = new Location(-17.690926, 135.675079, 3.955513);
        // Location end1 = new Location(65.233345, 137.465881, 2.788219);
        // Route route1 = new Route(1, "Town10HD", "Under_bridge", start1, end1, 200.0);
        // repo.createRoute(route1);

        // Location start2 = new Location(-45.582699, 56.939335, 2.462025);
        // Location end2 = new Location(-45.251133, -42.823887, 2.542971);
        // Route route2 = new Route(2, "Town10HD", "Intersection_1", start2, end2,
        // 200.0);
        // repo.createRoute(route2);

        // Location start3 = new Location(-45.251133, -42.823887, 2.542971);
        // Location end3 = new Location(68.561852, -59.963730, 2.660012);
        // Route route3 = new Route(3, "Town10HD", "Intersection_right_1", start3, end3,
        // 200.0);
        // repo.createRoute(route3);

        // Location start4 = new Location(23.217936, 307.255493, 2.992633);
        // Location end4 = new Location(108.279999, 307.859985, 4.310000);
        // Route route4 = new Route(4, "Town02", "Straight_across_apartment_block",
        // start4, end4,
        // 200.0);
        // repo.createRoute(route4);

        // Location start5 = new Location(1.528844, 305.996582, 2.647867);
        // Location end5 = new Location(46.184208, 255.601486, 2.790096);
        // Route route5 = new Route(5, "Town02", "Turn_left_intersection_1", start5,
        // end5,
        // 200.0);
        // repo.createRoute(route5);

        // Location start6 = new Location(46.184208, 255.601486, 2.790096);
        // Location end6 = new Location(118.666794, 240.717957, 2.537413);
        // Route route6 = new Route(6, "Town02", "Turn_right_intersection_1", start6,
        // end6,
        // 200.0);
        // repo.createRoute(route6);

        // Location start7 = new Location(44.070000, -5.840000, 4.150000);
        // Location end7 = new Location(6.361908, 33.454803, 2.649393);
        // Route route7 = new Route(7, "Town03", "Round_about_3rd_exit", start7,
        // end7,
        // 475.26);
        // repo.createRoute(route7);

        // Location start8 = new Location(44.070000, -5.840000, 4.150000);
        // Location end8 = new Location(-63.550354, -2.724823, 2.435900);
        // Route route8 = new Route(8, "Town03", "Round_about_2nd_exit", start8,
        // end8,
        // 520.36);
        // repo.createRoute(route8);

        // Location start9 = new Location(44.070000, -5.840000, 4.150000);
        // Location end9 = new Location(218.588593, 10.101290, 2.872400);
        // Route route9 = new Route(9, "Town03", "Round_about_1st_exit", start9,
        // end9,
        // 895.56);
        // repo.createRoute(route9);

        // ------------------------INFLUXDB WRITE------------------------------------

        // Set up server socket
        AsyncSocketReceiver receiver = new AsyncSocketReceiver();
        receiver.startReceiver(9000);

    }

}
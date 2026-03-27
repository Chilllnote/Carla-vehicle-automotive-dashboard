package com.example;

import com.example.Postgres_Objects.*;
import java.sql.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class PostgresRepository {
    // Get a connection string for Postgres
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/carla_db?user=postgres&password=mysecretpassword&&options=-c search_path=influxdb_postgres_template";
    private static final String Postgre_User = "postgres";
    private static final String Postgre_Password = "mysecretpassword";

    private Connection connection;

    public PostgresRepository() {
        try {
            this.connection = DriverManager.getConnection(Postgre_URL, Postgre_User, Postgre_Password);
            System.out.println("Database has been created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createEmployee(Employee employee) {
        // Implementation for creating a record in PostgreSQL
        String sql = """
                INSERT INTO employees (employeeid, firstname, lastname, email, Username, PasswordHash, permissionlevel, hiredate)
                VALUES ( ?, ?, ?, ?, ?, ?, ?::employee_permission, ?)
                ON CONFLICT (employeeid) DO NOTHING;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employee.getEmployeeID());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getUsername());
            stmt.setString(6, employee.getPasswordHash());
            stmt.setString(7, employee.getPermissionLevel());
            stmt.setTimestamp(8, employee.getHireDate());
            stmt.executeUpdate();
            System.out.println("Employee created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: Create parameters for vehicle
    public void createVehicle(Vehicle vehicle) {
        // Implementation for creating a vehicle record in PostgreSQL
        String sql = """
                INSERT INTO VEHICLES (VehicleID, Make, Model) VALUES ( ?, ?, ?)
                ON CONFLICT (VehicleID) DO NOTHING;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set parameters here
            stmt.setInt(1, vehicle.getVehicleID());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.execute();
            System.out.println("Vehicle created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createRoute(Route route) throws Exception {
        // Implementation for creating a route record in PostgreSQL
        ObjectMapper mapper = new ObjectMapper();

        String startJson = mapper.writeValueAsString(route.getStartLocation());
        String endJson = mapper.writeValueAsString(route.getEndLocation());
        String sql = """
                INSERT INTO Routes (RouteID, Town, routename, StartLocation, EndLocation, Distance) VALUES (?, ?, ?, ?::jsonb, ?::jsonb, ?)
                ON CONFLICT (RouteID) DO NOTHING;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, route.getRouteID());
            stmt.setString(2, route.getTown());
            stmt.setString(3, route.getName());
            stmt.setString(4, startJson);
            stmt.setString(5, endJson);
            stmt.setDouble(6, route.getDistance());
            stmt.execute();
            System.out.println("Route created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTrip(Trip trip) {
        // Implementation for creating a trip record in PostgreSQL

        if (!isValidJson(trip.getEvents().toString())) {
            System.out.println("Invalid JSON format for eventsJSON");
            return;
        }

        String sql = """
                INSERT INTO Trips (TripID, EmployeeID, VehicleID, RouteID, TripTestName, TripDate, StartTime, EndTime, Events)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?::jsonb)
                ON CONFLICT (TripID) DO NOTHING;
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, trip.getTripID());
            stmt.setInt(2, trip.getEmployeeID());
            stmt.setInt(3, trip.getVehicleID());
            stmt.setInt(4, trip.getRouteID());
            stmt.setString(5, trip.getTripTestName());
            stmt.setDate(6, trip.getTripDate());
            stmt.setTimestamp(7, trip.getStartTime());
            stmt.setTimestamp(8, trip.getEndTime());
            stmt.setString(9, trip.getEvents().toString());
            stmt.execute();
            System.out.println("Trip created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee readEmployee(int employee) throws SQLException {
        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employee);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getInt("EmployeeID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Email"),
                            rs.getString("Username"),
                            rs.getString("PasswordHash"),
                            rs.getString("PermissionLevel"),
                            rs.getTimestamp("HireDate"));
                }
            }
        }
        return null;
    }

    public List<Employee> readAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("PermissionLevel"),
                        rs.getTimestamp("HireDate")));
            }
        }
        return employees;
    }

    public String getEmployeeFirstName(int employeeID) throws SQLException {
        String sql = "SELECT FirstName FROM Employees WHERE EmployeeID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("FirstName");
                } else {
                    return null; // Or throw an exception if employee not found
                }
            }
        }
    }

    public Vehicle readVehicle(int vehicleID) throws SQLException {
        String sql = "SELECT * FROM Vehicles WHERE VehicleID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, vehicleID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Vehicle(
                            rs.getInt("VehicleID"),
                            rs.getString("Make"),
                            rs.getString("Model"));
                }
            }
        }
        return null;
    }

    public List<Vehicle> readAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getInt("VehicleID"),
                        rs.getString("Make"),
                        rs.getString("Model")));
            }
        }
        return vehicles;
    }

    public Route readRoute(int routeID) throws Exception {

        String sql = "SELECT * FROM Routes WHERE RouteID = ?";

        ObjectMapper mapper = new ObjectMapper();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, routeID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    String startJson = rs.getString("StartLocation");
                    String endJson = rs.getString("EndLocation");

                    Location startLocation = mapper.readValue(startJson, Location.class);
                    Location endLocation = mapper.readValue(endJson, Location.class);
                    return new Route(
                            rs.getInt("RouteID"),
                            rs.getString("Town"),
                            rs.getString("RouteName"), // IMPORTANT: column name is RouteName, not name
                            startLocation,
                            endLocation,
                            rs.getDouble("Distance"));
                }
            }
        }
        return null;
    }

    public List<Route> readAllRoutes() throws Exception {
        List<Route> routes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String sql = "SELECT * FROM Routes";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                String startJson = rs.getString("StartLocation");
                String endJson = rs.getString("EndLocation");

                Location startLocation = mapper.readValue(startJson, Location.class);
                Location endLocation = mapper.readValue(endJson, Location.class);
                routes.add(new Route(
                        rs.getInt("RouteID"),
                        rs.getString("Town"),
                        rs.getString("RouteName"), // IMPORTANT: column name is RouteName, not name
                        startLocation,
                        endLocation,
                        rs.getDouble("Distance")));
            }
        }
        return routes;
    }

    public Trip readTrip(int tripID) throws SQLException {
        String sql = "SELECT * FROM Trips WHERE TripID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tripID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    return new Trip(
                            rs.getInt("TripID"),
                            rs.getInt("EmployeeID"),
                            rs.getInt("VehicleID"),
                            rs.getInt("RouteID"),
                            rs.getString("TripTestName"),
                            rs.getDate("TripDate"),
                            rs.getTimestamp("StartTime"),
                            rs.getTimestamp("EndTime"),
                            rs.getString("Events"));
                }
            }
        }
        return null;
    }

    public List<Trip> readAllTrips() throws SQLException {
        List<Trip> trips = new ArrayList<>();
        String sql = "SELECT * FROM Trips";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                trips.add(new Trip(
                        rs.getInt("TripID"),
                        rs.getInt("EmployeeID"),
                        rs.getInt("VehicleID"),
                        rs.getInt("RouteID"),
                        rs.getString("TripTestName"),
                        rs.getDate("TripDate"),
                        rs.getTimestamp("StartTime"),
                        rs.getTimestamp("EndTime"),
                        rs.getString("Events")));
            }
        }
        return trips;
    }

    // ============== UPDATE METHODS ==============

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE Employees SET FirstName = ?, LastName = ?, Email = ?, " +
                "Username = ?, PasswordHash = ?, PermissionLevel = CAST(? AS employee_permission), HireDate = ? " +
                "WHERE EmployeeID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getUsername());
            stmt.setString(5, employee.getPasswordHash());
            stmt.setString(6, employee.getPermissionLevel());
            stmt.setTimestamp(7, employee.getHireDate());
            stmt.setInt(8, employee.getEmployeeID());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Update failed, no employee found with ID: " + employee.getEmployeeID());
            }
            System.out.println("Employee updated successfully. Rows affected: " + rowsAffected);
        }
    }

    public void updateVehicle(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE Vehicles SET Make = ?, Model = ? WHERE VehicleID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getMake());
            stmt.setString(2, vehicle.getModel());
            stmt.setInt(3, vehicle.getVehicleID());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Update failed, no vehicle found with ID: " + vehicle.getVehicleID());
            }
            System.out.println("Vehicle updated successfully. Rows affected: " + rowsAffected);
        }
    }

    public void updateRoute(Route route) throws SQLException {
        String sql = "UPDATE Routes SET Town = ?, StartLocation = ?::jsonb, EndLocation = ?::jsonb, Distance = ? " +
                "WHERE RouteID = ?";

        ObjectMapper mapper = new ObjectMapper(); // ideally move to a class field

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Convert Location → JSON
            String startJson = mapper.writeValueAsString(route.getStartLocation());
            String endJson = mapper.writeValueAsString(route.getEndLocation());

            stmt.setString(1, route.getTown());
            stmt.setString(2, startJson);
            stmt.setString(3, endJson);
            stmt.setDouble(4, route.getDistance());
            stmt.setInt(5, route.getRouteID());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Update failed, no route found with ID: " + route.getRouteID());
            }

            System.out.println("Route updated successfully. Rows affected: " + rowsAffected);
        } catch (Exception e) {
            throw new SQLException("Failed to serialize Location to JSON", e);
        }
    }

    // TODO: add check for past versions of Trip
    public void updateTrip(Trip trip) throws SQLException {
        String sql = "UPDATE Trips SET EmployeeID = ?, VehicleID = ?, RouteID = ?, TripTestName = ? " +
                "TripDate = ?, StartTime = ?, EndTime = ?, Events = ?::jsonb " +
                "WHERE TripID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, trip.getEmployeeID());
            stmt.setInt(2, trip.getVehicleID());
            stmt.setInt(3, trip.getRouteID());
            stmt.setString(4, trip.getTripTestName());
            stmt.setDate(5, trip.getTripDate());
            stmt.setTimestamp(6, trip.getStartTime());
            stmt.setTimestamp(7, trip.getEndTime());

            // Convert JsonNode to String for JSONB
            if (isValidJson(trip.getEvents().toString())) {
                stmt.setString(7, trip.getEvents().toString());
            } else {
                System.out.println("JSON object entered is not valid.");
            }
            stmt.setInt(8, trip.getTripID());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Update failed, no trip found with ID: " + trip.getTripID());
            }
            System.out.println("Trip updated successfully. Rows affected: " + rowsAffected);
        }
    }

    public void deleteEmployee(int EmployeeID) {
        // Implementation for deleting a record from PostgreSQL

        String sql = "DELETE FROM employees WHERE employeeid = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, EmployeeID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicle(int VehicleID) {
        // Implementation for deleting a vehicle record from PostgreSQL

        String sql = "DELETE FROM vehicles WHERE VehicleID = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, VehicleID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vehicle deleted successfully!");
            } else {
                System.out.println("No vehicle found with the given ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteRoute(int RouteID) {
        // Implementation for deleting a route record from PostgreSQL

        String sql = "DELETE FROM routes WHERE RouteID = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, RouteID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Route deleted successfully!");
            } else {
                System.out.println("No route found with the given ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTrip(int TripID) {
        // Implementation for deleting a trip record from PostgreSQL

        String sql = "DELETE FROM trips WHERE TripID = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, TripID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Trip deleted successfully!");
            } else {
                System.out.println("No trip found with the given ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------Controller necessary
    // queries------------------------------------------

    public int getEmployeeIDByUsername(String username) throws SQLException {
        String sql = "SELECT employeeid FROM Employees WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("employeeid"); // get the ID from the ResultSet
                } else {
                    throw new SQLException("No employee found with username: " + username);
                }
            }
        }
    }

    public Boolean findUser(String user) throws SQLException {
        String sql = "SELECT * FROM Employees WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            }
        }
    }

    public Boolean findUserPass(String user, String Pass) throws SQLException {
        String sql = "SELECT * FROM Employees WHERE username = ? AND passwordhash = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, Pass);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            }
        }
    }

    public Boolean validateRouteID(int ID) throws SQLException {
        String sql = "SELECT * FROM Routes WHERE routeid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            }
        }
    }

    public Boolean validateVehicleID(int ID) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE vehicleid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            }
        }
    }

    public Boolean validateEmployeeID(int ID) throws SQLException {
        String sql = "SELECT * FROM employees WHERE employeeid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            }
        }
    }

    public Boolean validateTripID(int ID) throws SQLException {
        String sql = "SELECT * FROM trips WHERE tripid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            }
        }
    }

    public boolean isUsernameAvailable(String username) throws SQLException {
        String sql = "SELECT 1 FROM Employees WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                // If rs.next() == true, the username already exists → return false
                return !rs.next();
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------

    /*
     * 
     * TODO: would allow you to load multiple records into database at a time.
     * public void loadRecord() {
     * // Implementation for loading records from PostgreSQL
     * }
     * 
     * public void saveRecord() {
     * // Implementation for saving records to PostgreSQL
     * }
     */

    public JSONObject listToJsonB(List<?> list) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray(list);
        json.put("Events", array);
        return json;
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

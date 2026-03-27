package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.spec.ECGenParameterSpec;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.Postgres_Objects.*;
import com.google.api.Property;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONObject;

import com.example.JSON_classes.configJson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class dashboardController {

    @FXML
    private Button mainQueryButton;

    @FXML
    private Button mainTablesButton;

    @FXML
    private Button Main_home_Button;

    @FXML
    private Button TripsMenuButton;

    @FXML
    private Button Create_Employee_Submit_button;

    @FXML
    private Button Create_Route_Submit_button;

    @FXML
    private TextField Create_Trip_route_ID_text_Field;

    @FXML
    private Button Create_Vehicle_Submit_button;

    @FXML
    private Pane Create_employee_panel;

    @FXML
    private Pane Create_route_pane;

    @FXML
    private Button Create_trip_submit_Button;

    @FXML
    private Button RemoveEmployeeButton;

    @FXML
    private Button Remove_Employee_Button_submit;

    @FXML
    private Label Remove_Employee_Label;

    @FXML
    private Pane Remove_Employee_Pane;

    @FXML
    private TextField Remove_Employee_Text_Field;

    @FXML
    private Label Remove_Route_text_label;

    @FXML
    private Button Remove_Vehicle_Button_Submit;

    @FXML
    private Pane Remove_Vehicle_Pane;

    @FXML
    private Label Remove_Vehicle_label;

    @FXML
    private Button Remove_route_Button_Submit;

    @FXML
    private Pane Remove_route_Pane;

    @FXML
    private TextField Remove_route_text_field;

    @FXML
    private TextField Remove_vehicle_text_field;

    @FXML
    private Button Update_Employee_Submit_button;

    @FXML
    private Button Update_Vehicle_Button_Submit;

    @FXML
    private Label Update_employee_label;

    @FXML
    private Pane Update_employee_pane;

    @FXML
    private Button Update_route_Button_submit;

    @FXML
    private Pane Update_route_pane;

    @FXML
    private TableColumn<Vehicle, Integer> Vehicle_table_vehicleID;

    @FXML
    private TableView<Vehicle> Vehicle_table;

    @FXML
    private TableColumn<Vehicle, String> Vehicle_table_Make;

    @FXML
    private TableColumn<Vehicle, String> Vehicle_table_model;

    @FXML
    private Button createEmployeeButton;

    @FXML
    private Button createRouteButton;

    @FXML
    private Button createVehicleButton;

    @FXML
    private TextField create_employee_ID;

    @FXML
    private TextField create_employee_email;

    @FXML
    private TextField create_employee_first_name;

    @FXML
    private TextField create_employee_hiredate;

    @FXML
    private Label create_employee_label;

    @FXML
    private TextField create_employee_last_name;

    @FXML
    private TextField create_employee_pass;

    @FXML
    private ChoiceBox<String> create_employee_permissions;

    @FXML
    private TextField create_employee_username;

    @FXML
    private TextField create_end_location_text_field;

    @FXML
    private TextField create_name_text_field;

    @FXML
    private TextField create_route_ID_text_field;

    @FXML
    private Label create_route_label;

    @FXML
    private TextField create_start_location_text_field;

    @FXML
    private TextField create_town_text_field;

    @FXML
    private Label create_trip_label;

    @FXML
    private TextField create_trip_name_text_field;

    @FXML
    private TextField create_trip_vehicle_ID_text_field;

    @FXML
    private Label create_vehicle_label;

    @FXML
    private Pane create_vehicle_pane;

    @FXML
    private Label employeeHomeLabel;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Integer> EmployeeID_column;

    @FXML
    private TableColumn<Employee, String> Employee_table_first_name;

    @FXML
    private TableColumn<Employee, String> Employee_table_Last_name;

    @FXML
    private TableColumn<Employee, String> Employee_table_Email;

    @FXML
    private TableColumn<Employee, String> Employee_table_User;

    @FXML
    private TableColumn<Employee, String> Employee_table_pass;

    @FXML
    private TableColumn<Employee, String> Employee_Table_Permissions;

    @FXML
    private TableColumn<Employee, Timestamp> Employee_Table_hiredate;

    @FXML
    private Button employeeTableButton;

    @FXML
    private AnchorPane homeWindow;

    @FXML
    private TextField make_text_field;

    @FXML
    private TextField model_text_field;

    @FXML
    private AnchorPane queriesWindow;

    @FXML
    private Button removeRouteButton;

    @FXML
    private Button removeVehicleButton;

    @FXML
    private TableView<Route> routeTable;

    @FXML
    private TableColumn<Route, Integer> route_table_ID;

    @FXML
    private TableColumn<Route, String> route_table_Name;

    @FXML
    private TableColumn<Route, String> route_table_Town;

    @FXML
    private TableColumn<Route, Double> route_table_distance;

    @FXML
    private TableColumn<Route, String> route_table_end_location;

    @FXML
    private TableColumn<Route, String> route_table_start_location;

    @FXML
    private Button routesTableButton;

    @FXML
    private AnchorPane tablesWindow;

    @FXML
    private TableView<Trip> tripsTable;

    @FXML
    private TableColumn<Trip, Integer> Trip_table_Employee_ID;

    @FXML
    private TableColumn<Trip, Integer> Trip_table_ID;

    @FXML
    private TableColumn<Trip, Integer> Trip_table_Route_ID;

    @FXML
    private TableColumn<Trip, String> Trip_table_Test_Name;

    @FXML
    private TableColumn<Trip, Timestamp> Trip_table_end_time;

    @FXML
    private TableColumn<Trip, String> Trip_table_events;

    @FXML
    private TableColumn<Trip, Timestamp> Trip_table_start_date;

    @FXML
    private TableColumn<Trip, Date> Trip_table_trip_date;

    @FXML
    private TableColumn<Trip, Integer> Trip_table_vehicle_ID;

    @FXML
    private Button tripsTableButton;

    @FXML
    private AnchorPane tripsWindow;

    @FXML
    private Button updateEmployeeButton;

    @FXML
    private Button updateRoutebutton;

    @FXML
    private Button updateVehicleButton;

    @FXML
    private TextField update_employee_ID;

    @FXML
    private TextField update_employee_email;

    @FXML
    private TextField update_employee_first_name;

    @FXML
    private TextField update_employee_hiredate;

    @FXML
    private TextField update_employee_last_name;

    @FXML
    private TextField update_employee_pass;

    @FXML
    private ChoiceBox<String> update_employee_permissions;

    @FXML
    private TextField update_employee_username;

    @FXML
    private TextField update_route_ID_text_field;

    @FXML
    private TextField update_route_end_field;

    @FXML
    private TextField update_route_distance_text_field;

    @FXML
    private Label update_route_label;

    @FXML
    private TextField update_route_name_text_field;

    @FXML
    private TextField update_route_start_field;

    @FXML
    private TextField update_route_town_text_field;

    @FXML
    private TextField update_vehicle_ID_text_field;

    @FXML
    private Label update_vehicle_label;

    @FXML
    private TextField update_vehicle_make_text_field;

    @FXML
    private TextField update_vehicle_model_text_field;

    @FXML
    private Pane update_vehicle_pane;

    @FXML
    private TextField vehicle_ID_text_field;

    @FXML
    private Button vehiclesTableButton;

    @FXML
    private TextField Create_Trip_ID_text_Field;

    @FXML
    private TextField Create_Trip_Num_ID_text_Field;

    @FXML
    private TextField create_Distance_text_field;

    private List<Pane> allPanes;
    private List<Pane> queryPanes;
    private List<TableView> tablePanes;
    private List<String> permissions = List.of("ADMIN", "USER", "VIEW");
    private InfluxDBRepository influxRepo = new InfluxDBRepository();
    private PostgresRepository postRepo = new PostgresRepository();

    private int EmployeeID;

    @FXML
    public void initialize() {

        queryPanes = List.of(
                Create_employee_panel,
                Create_route_pane,
                create_vehicle_pane, // Done
                Remove_Employee_Pane,
                Remove_route_Pane,
                Remove_Vehicle_Pane,
                Update_employee_pane,
                Update_route_pane,
                update_vehicle_pane);

        allPanes = List.of(
                Create_employee_panel,
                Create_route_pane,
                create_vehicle_pane,
                Remove_Employee_Pane,
                Remove_route_Pane,
                Remove_Vehicle_Pane,
                Update_employee_pane,
                Update_route_pane,
                update_vehicle_pane,
                homeWindow,
                queriesWindow,
                tablesWindow,
                tripsWindow);

        tablePanes = List.of(
                Vehicle_table,
                employeeTable,
                routeTable,
                tripsTable);

        employeeHomeLabel.setText("Let's get testing");
        switchMainPane(homeWindow);

        // Initialize dropbox menu
        create_employee_permissions.getItems().addAll(permissions);
        update_employee_permissions.getItems().addAll(permissions);

        // Initalize columns (what part of the object corresponds to what column)
        EmployeeID_column.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeID"));
        Employee_table_first_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        Employee_table_Last_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        Employee_table_Email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        Employee_table_User.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        Employee_table_pass.setCellValueFactory(new PropertyValueFactory<Employee, String>("passwordhash"));
        Employee_Table_Permissions.setCellValueFactory(new PropertyValueFactory<Employee, String>("permissionLevel"));
        Employee_Table_hiredate.setCellValueFactory(new PropertyValueFactory<Employee, Timestamp>("hireDate"));

        Vehicle_table_vehicleID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("vehicleID"));
        Vehicle_table_Make.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("make"));
        Vehicle_table_model.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));

        Trip_table_ID.setCellValueFactory(new PropertyValueFactory<Trip, Integer>("tripID"));
        Trip_table_Employee_ID.setCellValueFactory(new PropertyValueFactory<Trip, Integer>("employeeID"));
        Trip_table_vehicle_ID.setCellValueFactory(new PropertyValueFactory<Trip, Integer>("vehicleID"));
        Trip_table_Route_ID.setCellValueFactory(new PropertyValueFactory<Trip, Integer>("routeID"));
        Trip_table_Test_Name.setCellValueFactory(new PropertyValueFactory<Trip, String>("tripTestName"));
        Trip_table_trip_date.setCellValueFactory(new PropertyValueFactory<Trip, Date>("tripDate"));
        Trip_table_start_date.setCellValueFactory(new PropertyValueFactory<Trip, Timestamp>("startTime"));
        Trip_table_end_time.setCellValueFactory(new PropertyValueFactory<Trip, Timestamp>("endTime"));
        Trip_table_events.setCellValueFactory(new PropertyValueFactory<Trip, String>("events"));

        route_table_ID.setCellValueFactory(new PropertyValueFactory<Route, Integer>("routeID"));
        route_table_Town.setCellValueFactory(new PropertyValueFactory<Route, String>("town"));
        route_table_Name.setCellValueFactory(new PropertyValueFactory<Route, String>("name"));
        route_table_start_location.setCellValueFactory(new PropertyValueFactory<Route, String>("startLocation"));
        route_table_end_location.setCellValueFactory(new PropertyValueFactory<Route, String>("endLocation"));
        route_table_distance.setCellValueFactory(new PropertyValueFactory<Route, Double>("distance"));

    }

    // Switch the main pane and reset everything
    private void switchMainPane(Pane target) {
        for (Pane p : allPanes) {
            p.setVisible(false);
            p.setManaged(false); // removes layout space
        }
        hideTables();

        target.setVisible(true);
        target.setManaged(true);
    }

    // Switch only the QueryPane (Use only within query window)
    private void switchQueryPane(Pane target) {
        for (Pane p : queryPanes) {
            p.setVisible(false);
            p.setManaged(false); // removes layout space
        }

        target.setVisible(true);
        target.setManaged(true);
    }

    private void hideTables() {
        for (TableView t : tablePanes) {
            t.setVisible(false);
            t.setManaged(false);
        }
    }

    private void switchTable(TableView table) {
        hideTables();
        table.setVisible(true);
        table.setManaged(true);
    }

    @FXML
    void OnCreateTripSubmitButton(ActionEvent event) throws Exception {
        // Validate Trip inputs

        // send inputs into getTripConfig
        // Create trips configs beforehand
        // Query for route
        // start place
        // Town
        // Query for Vehicle
        // Make, model

        List<String> events = new ArrayList<>();
        int tripID = 0;
        int routeID = 0;
        int vehicleID = 0;

        try {
            tripID = Integer.parseInt(Create_Trip_Num_ID_text_Field.getText());
        } catch (NumberFormatException e) {
            create_trip_label.setText("Your trip Id is not a valid int");
        }

        try {
            routeID = Integer.parseInt(Create_Trip_route_ID_text_Field.getText());
        } catch (NumberFormatException e) {
            create_trip_label.setText("Your route ID is not a valid int");
        }

        try {
            vehicleID = Integer.parseInt(create_trip_vehicle_ID_text_field.getText());
        } catch (NumberFormatException e) {
            create_trip_label.setText("VehicleID is not a valid int");
        }

        String tripName = create_trip_name_text_field.getText();

        Route routeGiven = this.postRepo.readRoute(routeID);
        Vehicle vehicleGiven = this.postRepo.readVehicle(vehicleID);
        Timestamp startTime = null;

        if (Create_Trip_Num_ID_text_Field.getText().isEmpty()) {
            create_trip_label.setText("Please write a Trip Id number");
        } else if (this.postRepo.validateTripID(tripID)) {
            create_trip_label.setText("That trip number ID is already taken");
        } else if (create_trip_vehicle_ID_text_field.getText().isEmpty()) {
            create_trip_label.setText("Please write a vehicle ID");
        } else if (!this.postRepo.validateVehicleID(vehicleID)) {
            create_trip_label.setText("No vehicle matches that ID");
        } else if (Create_Trip_route_ID_text_Field.getText().isBlank()) {
            create_trip_label.setText("Please write a route ID");
        } else if (!this.postRepo.validateRouteID(routeID)) {
            create_trip_label.setText("No route matches that ID");
        } else if (tripName.isBlank()) {
            create_trip_label.setText("Please enter a value into tripName");
        } else if (create_trip_name_text_field.getText().isBlank()) {
            create_trip_label.setText("Please enter a trip name in text field");
        } else {

            create_trip_label.setText("Trip starting......");

            try (ServerSocket serverSocket = new ServerSocket(9000)) {
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

                JSONObject json = new JSONObject(
                        new configJson(
                                new Location(routeGiven.getStartLocation().getX(),
                                        routeGiven.getStartLocation().getY(), routeGiven.getStartLocation().getZ()),
                                routeGiven.getTown(),
                                vehicleGiven.getMake(),
                                vehicleGiven.getModel()).toJson());

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

                startTime = Timestamp.from(Instant.now());
                String line;
                while ((line = reader.readLine()) != null) {
                    JSONObject obj = new JSONObject(line);

                    System.out.println(obj.toString());

                    if ("collision".equals(obj.getString("type"))) {
                        // Handle collision event if needed

                        if (events.isEmpty()) {
                            events.add("Collision");
                        }

                        this.influxRepo.createCollision(new collisionObj(
                                obj.getString("type"),
                                obj.getString("actor"),
                                new Date(obj.getLong("time")),
                                obj.getDouble("impact_force")), Create_Trip_ID_text_Field.getText(),
                                routeGiven.getTown());

                    } else {

                        this.influxRepo.createTrip(new Telemetry(
                                obj.getString("type"),
                                obj.getDouble("throttle"),
                                obj.getDouble("steer"),
                                obj.getDouble("brake"),
                                obj.getDouble("speed"),
                                obj.getJSONObject("location").getDouble("x"),
                                obj.getJSONObject("location").getDouble("y"),
                                obj.getJSONObject("location").getDouble("z"),
                                obj.getString("town")), Create_Trip_ID_text_Field.getText());
                    }
                }

                if (events.isEmpty()) {
                    events.add("");
                }

                // ---------------------------------------------

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                create_trip_label.setText("Trip has ended!");

                Timestamp endtime = Timestamp.from(Instant.now());

                Trip tripNow = new Trip(Integer.parseInt(Create_Trip_Num_ID_text_Field.getText()), this.EmployeeID,
                        vehicleID, routeID, tripName, new Date(System.currentTimeMillis()), startTime, endtime,
                        this.postRepo.listToJsonB(events).toString());
                this.postRepo.createTrip(tripNow);
            }
        }

    }

    @FXML
    void OnCreateVehicleChoiceButton(ActionEvent event) {
        // Validate user input

        // Create vehicle object
        // Create vehicle within postgres
        switchQueryPane(create_vehicle_pane);
    }

    @FXML
    void OnUpdateRouteSubmitButton(ActionEvent event) throws Exception {
        // Validate user input
        // Create route object
        // Update route on routeID
        try {

            int routeNum = Integer.parseInt(update_route_ID_text_field.getText());
            String town = update_route_town_text_field.getText();
            String routeName = update_route_name_text_field.getText();
            double Distance = 0.0;

            try {
                Distance = Double.parseDouble(update_route_distance_text_field.getText());

            } catch (NumberFormatException e) {
                update_route_label.setText("Distance is not a valid double value. Please enter a valid value");
            }

            if (!this.postRepo.validateRouteID(routeNum)) {
                update_route_label.setText("Route ID not found. Please enter valid Route ID");
            } else if (!isValidLocation(update_route_start_field.getText())) {
                update_route_label.setText("Start location not a valid string. Please enter valid location");
            } else if (isValidLocation(update_route_end_field.getText())) {
                update_route_label.setText("End field not a valid string. Please enter a valid location");
            } else {
                Route updateRoute = new Route(routeNum, town, routeName,
                        parseLocation(update_route_start_field.getText()),
                        parseLocation(update_route_end_field.getText()), Distance);
                this.postRepo.updateRoute(updateRoute);
                update_route_label.setText("Route has been updated!");
            }

        } catch (NumberFormatException e) {
            update_route_label.setText("You must enter a valid Integer");
        }

    }

    @FXML
    void onCreateEmployeeChoiceButton(ActionEvent event) {
        // Validate input
        switchQueryPane(Create_employee_panel);

    }

    // ADD USERNAME VALIDDATION
    // ADD EMAIL VALIDATION
    @FXML
    void onCreateEmployeeSubmitButton(ActionEvent event) throws Exception {
        // Validate input
        try {

            int EmployeeID = Integer.parseInt(create_employee_ID.getText());
            String firstName = create_employee_first_name.getText();
            String lastName = create_employee_last_name.getText();
            String email = create_employee_email.getText();
            String username = create_employee_username.getText();
            String passwordHash = create_employee_pass.getText();
            String permissionLevel = create_employee_permissions.getValue();

            if (firstName.isBlank()) {
                create_employee_label.setText("Please enter a firstname");
            } else if (this.postRepo.validateEmployeeID(EmployeeID)) {
                create_employee_label.setText("That employee ID already exists. Please choose another onen.");
            } else if (lastName.isBlank()) {
                create_employee_label.setText("Please enter a last name");
            } else if (email.isBlank()) {
                create_employee_label.setText("Please enter an email");
            } else if (username.isBlank()) {
                create_employee_label.setText("Please enter a proper username");
            } else if (!this.postRepo.isUsernameAvailable(username)) {
                create_employee_label.setText("That username is already taken. Please write a new one");
            } else if (passwordHash.isBlank()) {
                create_employee_label.setText("Please enter a password");
            } else if (permissionLevel.isBlank()) {
                create_employee_label.setText("Please set a given permission level");
            } else if (!isTimestamp(create_employee_hiredate.getText())) {
                create_employee_label.setText("Value given for hire Date is not a proper date val");
            } else {
                Employee employee = new Employee(EmployeeID, firstName, lastName, email, username, passwordHash,
                        permissionLevel, Timestamp.valueOf(create_employee_hiredate.getText()));
                this.postRepo.createEmployee(employee);
                create_employee_label.setText("Employee has been created!");
            }

        } catch (NumberFormatException e) {
            create_employee_label.setText("You must enter a valid Integer for EmployeeID");
        }

    }

    @FXML
    void onCreateRouteChoiceButton(ActionEvent event) {
        // Validate input
        switchQueryPane(Create_route_pane);
    }

    @FXML
    void onCreateRouteSubmitButton(ActionEvent event) throws Exception {
        // Validate input

        try {

            int routeID = Integer.parseInt(create_route_ID_text_field.getText());
            String Town = create_town_text_field.getText();
            String routeName = create_name_text_field.getText();
            double Distance = 0;

            try {
                Distance = Double.parseDouble(create_Distance_text_field.getText());
            } catch (NumberFormatException e) {
                create_route_label.setText("Please enter a valid double value within the Distance field");
            }

            if (create_route_ID_text_field.getText().isBlank()) {
                create_route_label.setText("Please enter an ID number");
            } else if (this.postRepo.validateRouteID(routeID)) {
                create_route_label.setText("This route Id already exists. Please entera another one");
            } else if (Town.isBlank()) {
                create_route_label.setText("Please enter a valid Townn");
            } else if (routeName.isBlank()) {
                create_route_label.setText("Please enter a route Name");
            } else if (!isValidLocation(create_start_location_text_field.getText())) {
                create_route_label
                        .setText("Start location entered isn't valid. Please enter 3 comma separated double values");
            } else if (!isValidLocation(create_end_location_text_field.getText())) {
                create_route_label
                        .setText("End location entered isn't valid. Please enter 3 comma separated double values");
            } else if (Distance == 0.0) {
                create_route_label.setText("Please enter a valid Distance");
            } else {
                Route createRoute = new Route(routeID, Town, routeName,
                        parseLocation(create_start_location_text_field.getText()),
                        parseLocation(create_end_location_text_field.getText()), Distance);
                this.postRepo.createRoute(createRoute);
                create_route_label.setText("Route has been created!");
            }

        } catch (NumberFormatException e) {
            create_route_label.setText("Please enter a valid int for your route");
        }
    }

    @FXML
    void onCreateVehicleSubmitButton(ActionEvent event) throws Exception {
        // Validate input
        try {
            int VehicleID = Integer.parseInt(vehicle_ID_text_field.getText());
            String make = make_text_field.getText();
            String model = model_text_field.getText();

            if (this.postRepo.validateVehicleID(VehicleID)) {
                create_vehicle_label.setText("Vehicle ID already exists. Please write another");
            } else if (make.isEmpty()) {
                create_vehicle_label.setText("Please enter a valid make for your vehicle");
            } else if (model.isEmpty()) {
                create_vehicle_label.setText("Please write a valid model");
            } else {
                Vehicle vehilceCreate = new Vehicle(VehicleID, make, model);
                this.postRepo.createVehicle(vehilceCreate);
                create_vehicle_label.setText("Vehicle has been created!");
            }

        } catch (NumberFormatException e) {
            create_vehicle_label.setText("Please enter a valid int for your Vehicle ID");
        }
    }

    @FXML
    void onEmployeesTableButton(ActionEvent event) throws Exception {
        // Query List of employee objects from Postgres

        hideTables();
        switchTable(employeeTable);
        employeeTable.getItems().setAll(this.postRepo.readAllEmployees());

    }

    @FXML
    void onRemoveEmployeeChoiceButton(ActionEvent event) {
        // Validate input
        switchQueryPane(Remove_Employee_Pane);
    }

    @FXML
    void onRemoveEmployeeSubmitButton(ActionEvent event) throws Exception {
        // Validate input
        // Should not be able to remove yourself
        try {
            int EmployeeID = Integer.parseInt(Remove_Employee_Text_Field.getText());

            if (!this.postRepo.validateEmployeeID(EmployeeID)) {
                Remove_Employee_Label.setText("Employee ID doesn't exist. Please select a valid Employee ID");
            } else {
                this.postRepo.deleteEmployee(EmployeeID);
                Remove_Employee_Label.setText("Employee has been removed!");
            }
        } catch (NumberFormatException e) {
            Remove_Employee_Label.setText("Please enter an Integer number for Employee ID");
        }
    }

    @FXML
    void onRemoveRouteChoiceButton(ActionEvent event) {
        // Validate input
        switchQueryPane(Remove_route_Pane);
    }

    @FXML
    void onRemoveRouteSubmitButton(ActionEvent event) throws Exception {
        // Validate input
        try {
            int RouteID = Integer.parseInt(Remove_route_text_field.getText());

            if (!this.postRepo.validateRouteID(RouteID)) {
                Remove_Route_text_label.setText("Route ID doesn't exist. Please enter valid Route");
            } else {
                this.postRepo.deleteRoute(RouteID);
                Remove_Route_text_label.setText("Route has been removed!");
            }
        } catch (NumberFormatException e) {
            Remove_Route_text_label.setText("Please enter an Integer number");
        }
    }

    @FXML
    void onRemoveVehicleButtonSubmit(ActionEvent event) throws Exception {
        // Validate input
        try {
            int VehilceID = Integer.parseInt(Remove_vehicle_text_field.getText());

            if (!this.postRepo.validateVehicleID(VehilceID)) {
                Remove_Vehicle_label.setText("Vehilce ID doesn't exist. Please enter valid Route");
            } else {
                this.postRepo.deleteVehicle(VehilceID);
                Remove_Vehicle_label.setText("Vehilce has been removed!");
            }
        } catch (NumberFormatException e) {
            Remove_Vehicle_label.setText("Please enter an Integer number");
        }
    }

    @FXML
    void onRemoveVehicleChoiceButton(ActionEvent event) {
        // Validate input
        switchQueryPane(Remove_Vehicle_Pane);
    }

    @FXML
    void onRoutesTableButton(ActionEvent event) throws Exception {
        // Query List of Route objects from Postgres
        hideTables();
        switchTable(routeTable);
        routeTable.getItems().setAll(this.postRepo.readAllRoutes());
    }

    @FXML
    void onTripsTableButton(ActionEvent event) throws Exception {
        // Query List of Trip objects from Postgres
        hideTables();
        switchTable(tripsTable);
        tripsTable.getItems().setAll(this.postRepo.readAllTrips());

    }

    @FXML
    void onUpdateEmployeeChoiceButton(ActionEvent event) {
        // Validate input
        switchQueryPane(Update_employee_pane);
    }

    @FXML
    void onUpdateEmployeeSubmitButton(ActionEvent event) throws Exception {
        // Validate input
        try {

            int EmployeeID = Integer.parseInt(update_employee_ID.getText());
            String firstName = update_employee_first_name.getText();
            String lastName = update_employee_last_name.getText();
            String email = update_employee_email.getText();
            String username = update_employee_username.getText();
            String passwordHash = update_employee_pass.getText();
            String permissionLevel = update_employee_permissions.getValue();

            if (firstName.isBlank()) {
                Update_employee_label.setText("Please enter a firstname");
            } else if (!this.postRepo.validateEmployeeID(EmployeeID)) {
                Update_employee_label.setText("That employee ID doesn't exist. Please choose another onen.");
            } else if (lastName.isBlank()) {
                Update_employee_label.setText("Please enter a last name");
            } else if (email.isBlank()) {
                Update_employee_label.setText("Please enter an email");
            } else if (username.isBlank()) {
                Update_employee_label.setText("Please enter a proper username");
            } else if (!this.postRepo.isUsernameAvailable(username)) {
                Update_employee_label.setText("That username is already taken. Please write a new one");
            } else if (passwordHash.isBlank()) {
                Update_employee_label.setText("Please enter a password");
            } else if (permissionLevel.isBlank()) {
                Update_employee_label.setText("Please set a given permission level");
            } else if (!isTimestamp(create_employee_hiredate.getText())) {
                Update_employee_label.setText("Value given for hire Date is not a proper date val");
            } else {
                Employee employee = new Employee(EmployeeID, firstName, lastName, email, username, passwordHash,
                        permissionLevel, Timestamp.valueOf(create_employee_hiredate.getText()));
                this.postRepo.updateEmployee(employee);
                Update_employee_label.setText("Employee has been updated!");
            }

        } catch (NumberFormatException e) {
            create_employee_label.setText("You must enter a valid Integer for EmployeeID");
        }

    }

    @FXML
    void onUpdateRouteChoiceButton(ActionEvent event) {
        // Validate input
        switchQueryPane(Update_route_pane);
    }

    @FXML
    void onUpdateVehicleChoiceButton(ActionEvent event) {
        // Validate input
        switchQueryPane(update_vehicle_pane);
    }

    @FXML
    void onUpdateVehicleSubmitButton(ActionEvent event) throws Exception {
        // Validate input
        try {
            int VehicleID = Integer.parseInt(update_vehicle_ID_text_field.getText());
            String make = update_vehicle_make_text_field.getText();
            String model = update_vehicle_model_text_field.getText();

            if (!this.postRepo.validateVehicleID(VehicleID)) {
                update_vehicle_label.setText("Vehicle ID doesn't exist. Please write another");
            } else if (make.isEmpty()) {
                update_vehicle_label.setText("Please enter a valid make for your vehicle");
            } else if (model.isEmpty()) {
                update_vehicle_label.setText("Please write a valid model");
            } else {
                Vehicle vehilceUpdate = new Vehicle(VehicleID, make, model);
                this.postRepo.updateVehicle(vehilceUpdate);
                update_vehicle_label.setText("Vehicle has been updated!");
            }

        } catch (NumberFormatException e) {
            update_vehicle_label.setText("Please enter a valid int for your Vehicle ID");
        }
    }

    @FXML
    void onVehiclesTableButton(ActionEvent event) throws Exception {
        // Query List of Vehicle objects from Postgres
        hideTables();
        switchTable(Vehicle_table);
        Vehicle_table.getItems().setAll(this.postRepo.readAllVehicles());

    }

    @FXML
    void onHomeMenuClick(ActionEvent event) {
        switchMainPane(homeWindow);
        try {
            employeeHomeLabel.setText("What's next " + this.postRepo.getEmployeeFirstName(this.EmployeeID) + "?");
        } catch (Exception e) {
            employeeHomeLabel.setText("Hello Employee!");
        }
    }

    @FXML
    void onQueryMenuClick(ActionEvent event) {
        switchMainPane(queriesWindow);
    }

    @FXML
    void onTablesMenuClick(ActionEvent event) {
        switchMainPane(tablesWindow);
    }

    @FXML
    void onTripsMenuClick(ActionEvent event) {
        switchMainPane(tripsWindow);
    }

    public void setEmployeeID(int Id) {
        this.EmployeeID = Id;
    }

    private static boolean isInteger(String s) {
        if (s == null)
            return false;
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isTimestamp(String s) {
        if (s == null)
            return false;
        try {
            Timestamp.valueOf(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isDate(String s) {
        if (s == null)
            return false;
        try {
            Date.valueOf(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidLocation(String s) {
        if (s == null || s.isEmpty())
            return false;

        String[] parts = s.split(",");
        if (parts.length != 3)
            return false;

        for (String part : parts) {
            String trimmed = part.trim();

            // Reject integers (must contain decimal point)
            if (trimmed.matches("[-+]?\\d+"))
                return false;

            // Check if valid double
            try {
                Double.parseDouble(trimmed);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private static Location parseLocation(String s) {
        if (!isValidLocation(s)) {
            throw new IllegalArgumentException("Invalid location string: " + s);
        }

        String[] parts = s.split(",");
        double x = Double.parseDouble(parts[0].trim());
        double y = Double.parseDouble(parts[1].trim());
        double z = Double.parseDouble(parts[2].trim());

        return new Location(x, y, z);
    }

}

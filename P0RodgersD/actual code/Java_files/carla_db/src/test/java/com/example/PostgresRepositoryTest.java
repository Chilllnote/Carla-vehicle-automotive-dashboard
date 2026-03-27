package com.example;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import com.example.Postgres_Objects.Employee;
import com.example.Postgres_Objects.Location;
import com.example.Postgres_Objects.Route;
import com.example.Postgres_Objects.Vehicle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@ExtendWith(MockitoExtension.class)

// ### TO DO
// We need to create an interface with a respository that wil allow us to finish
// up these tests.
// without one, we can't create anything in memory

public class PostgresRepositoryTest {

        @Mock
        private PostgresRepository mockRepo;

        // Arrange and act before all tests here
        @BeforeEach
        public void setup() throws SQLException {

                Employee employee1 = new Employee(1, "John", "Doe", "john.doe@gmail.com", "jdoe", "password123",
                                "ADMIN",
                                new Timestamp(0));
                Employee employee2 = new Employee(2, "Jane", "Smith", "jane.smith@gmail.com", "jsmith", "password123",
                                "ADMIN",
                                new Timestamp(0));
                Employee employee3 = new Employee(3, "Michael", "Johnson", "michael.johnson@gmail.com", "mjohnson",
                                "password123", "ADMIN", new Timestamp(0));
                Employee employee4 = new Employee(4, "Emily", "Davis", "emily.davis@gmail.com", "edavis", "password123",
                                "ADMIN", new Timestamp(0));
                Employee employee5 = new Employee(5, "William", "Brown", "will.brown@gmail.com", "wbrown",
                                "password123",
                                "ADMIN", new Timestamp(0));
                Employee employee6 = new Employee(6, "Olivia", "Wilson", "olivia.wilson@gmail.com", "owilson",
                                "password123",
                                "ADMIN", new Timestamp(0));
                Employee employee7 = new Employee(7, "James", "Taylor", "james.taylor@gmail.com", "jtaylor",
                                "password123",
                                "ADMIN", new Timestamp(0));
                Employee employee8 = new Employee(8, "Sophia", "Anderson", "sophia.anderson@gmail.com", "sanderson",
                                "password123", "ADMIN", new Timestamp(0));
                Employee employee9 = new Employee(9, "Benjamin", "Thomas", "ben.thomas@gmail.com", "bthomas",
                                "password123",
                                "ADMIN", new Timestamp(0));
                Employee employee10 = new Employee(10, "Ava", "Moore", "ava.moore@gmail.com", "amoore", "password123",
                                "ADMIN",
                                new Timestamp(System.currentTimeMillis()));

                Vehicle vehicle1 = new Vehicle(1, "Toyota", "Prius");
                Vehicle vehicle2 = new Vehicle(2, "Ford", "Mustang");
                Vehicle vehicle3 = new Vehicle(3, "Tesla", "Model3");
                Vehicle vehicle4 = new Vehicle(4, "BMW", "X5");
                Vehicle vehicle5 = new Vehicle(5, "Audi", "A4");
                Vehicle vehicle6 = new Vehicle(6, "Mercedes", "C300");
                Vehicle vehicle7 = new Vehicle(7, "Honda", "Civic");
                Vehicle vehicle8 = new Vehicle(8, "Chevrolet", "Impala");
                Vehicle vehicle9 = new Vehicle(9, "Nissan", "Altima");
                Vehicle vehicle10 = new Vehicle(10, "Volkswagen", "Golf");

                Route route1 = new Route(1, "TownA", "Route1", new Location(0.0, 0.0, 0.0), new Location(1.0, 1.0, 1.0),
                                100);
                Route route2 = new Route(2, "TownB", "Route2", new Location(2.0, 2.0, 2.0), new Location(3.0, 3.0, 3.0),
                                150);
                Route route3 = new Route(3, "TownC", "Route3", new Location(4.0, 4.0, 4.0), new Location(5.0, 5.0, 5.0),
                                200);
                Route route4 = new Route(4, "TownD", "Route4", new Location(6.0, 6.0, 6.0), new Location(7.0, 7.0, 7.0),
                                250);
                Route route5 = new Route(5, "TownE", "Route5", new Location(8.0, 8.0, 8.0), new Location(9.0, 9.0, 9.0),
                                300);
                Route route6 = new Route(6, "TownF", "Route6", new Location(10.0, 10.0, 10.0),
                                new Location(11.0, 11.0, 11.0),
                                120);
                Route route7 = new Route(7, "TownG", "Route7", new Location(12.0, 12.0, 12.0),
                                new Location(13.0, 13.0, 13.0),
                                180);
                Route route8 = new Route(8, "TownH", "Route8", new Location(14.0, 14.0, 14.0),
                                new Location(15.0, 15.0, 15.0),
                                220);
                Route route9 = new Route(9, "TownI", "Route9", new Location(16.0, 16.0, 16.0),
                                new Location(17.0, 17.0, 17.0),
                                260);
                Route route10 = new Route(10, "TownJ", "Route10", new Location(18.0, 18.0, 18.0),
                                new Location(19.0, 19.0, 19.0), 300);

                mockRepo.createEmployee(employee1);
                mockRepo.createEmployee(employee2);
                mockRepo.createEmployee(employee3);
                mockRepo.createEmployee(employee4);
                mockRepo.createEmployee(employee5);
                mockRepo.createEmployee(employee6);
                mockRepo.createEmployee(employee7);
                mockRepo.createEmployee(employee8);
                mockRepo.createEmployee(employee9);
                mockRepo.createEmployee(employee10);

                mockRepo.createVehicle(vehicle1);
                mockRepo.createVehicle(vehicle2);
                mockRepo.createVehicle(vehicle3);
                mockRepo.createVehicle(vehicle4);
                mockRepo.createVehicle(vehicle5);
                mockRepo.createVehicle(vehicle6);
                mockRepo.createVehicle(vehicle7);
                mockRepo.createVehicle(vehicle8);
                mockRepo.createVehicle(vehicle9);
                mockRepo.createVehicle(vehicle10);

                try {
                        mockRepo.createRoute(route1);
                        mockRepo.createRoute(route2);
                        mockRepo.createRoute(route3);
                        mockRepo.createRoute(route4);
                        mockRepo.createRoute(route5);
                        mockRepo.createRoute(route6);
                        mockRepo.createRoute(route7);
                        mockRepo.createRoute(route8);
                        mockRepo.createRoute(route9);
                        mockRepo.createRoute(route10);
                } catch (Exception e) {
                        System.out.println("Couldn't create routes");
                }

                when(mockRepo.readEmployee(1)).thenReturn(employee1);
                when(mockRepo.readEmployee(2)).thenReturn(employee1);
                when(mockRepo.readEmployee(3)).thenReturn(employee1);
                when(mockRepo.readEmployee(4)).thenReturn(employee1);
                when(mockRepo.readEmployee(5)).thenReturn(employee1);
                when(mockRepo.readEmployee(6)).thenReturn(employee1);
                when(mockRepo.readEmployee(7)).thenReturn(employee1);
                when(mockRepo.readEmployee(8)).thenReturn(employee1);
                when(mockRepo.readEmployee(9)).thenReturn(employee1);
                when(mockRepo.readEmployee(10)).thenReturn(employee1);

                when(mockRepo.readVehicle(1)).thenReturn(vehicle1);
                when(mockRepo.readVehicle(2)).thenReturn(vehicle2);
                when(mockRepo.readVehicle(3)).thenReturn(vehicle3);
                when(mockRepo.readVehicle(4)).thenReturn(vehicle4);
                when(mockRepo.readVehicle(5)).thenReturn(vehicle5);
                when(mockRepo.readVehicle(6)).thenReturn(vehicle6);
                when(mockRepo.readVehicle(7)).thenReturn(vehicle7);
                when(mockRepo.readVehicle(8)).thenReturn(vehicle8);
                when(mockRepo.readVehicle(9)).thenReturn(vehicle9);
                when(mockRepo.readVehicle(10)).thenReturn(vehicle10);

                try {
                        when(mockRepo.readRoute(1)).thenReturn(route1);
                        when(mockRepo.readRoute(2)).thenReturn(route2);
                        when(mockRepo.readRoute(3)).thenReturn(route3);
                        when(mockRepo.readRoute(4)).thenReturn(route4);
                        when(mockRepo.readRoute(5)).thenReturn(route5);
                        when(mockRepo.readRoute(6)).thenReturn(route6);
                        when(mockRepo.readRoute(7)).thenReturn(route7);
                        when(mockRepo.readRoute(8)).thenReturn(route8);
                        when(mockRepo.readRoute(9)).thenReturn(route9);
                        when(mockRepo.readRoute(10)).thenReturn(route10);

                        when(mockRepo.validateEmployeeID(1)).thenReturn(true);
                        when(mockRepo.validateRouteID(1)).thenReturn(true);
                        when(mockRepo.validateVehicleID(1)).thenReturn(true);
                } catch (Exception e) {
                        System.out.println("Couldn't create when mock loop");
                }
                // Act
        }

        @Test
        void testFindUser() throws Exception {

                Employee e = mockRepo.readEmployee(1);
                assertEquals(e.getUsername(), "jdoe");

        }

        @Test
        void idValidations() throws Exception {

                assertEquals(true, mockRepo.validateEmployeeID(1));
                assertEquals(true, mockRepo.validateRouteID(1));
                assertEquals(true, mockRepo.validateVehicleID(1));
        }

        @Test
        void testFindUserPass() {

        }

        @Test
        void testReadAllEmployees() {

        }

        @Test
        void testReadAllRoutes() {

        }

        @Test
        void testReadAllTrips() {

        }

        @Test
        void testReadAllVehicles() {

        }

        @Test
        void testReadEmployee() {

        }

        @Test
        void testReadRoute() {

        }

        @Test
        void testReadTrip() {

        }

        @Test
        void testReadVehicle() {

        }

        @Test
        void testUpdateEmployee() {

        }

        @Test
        void testUpdateRoute() {

        }

        @Test
        void testUpdateTrip() {

        }

        @Test
        void testUpdateVehicle() {

        }
}

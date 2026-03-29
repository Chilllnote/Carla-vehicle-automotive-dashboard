package com.example.Employee_metadata.Config;

import com.example.Employee_metadata.Entity.Algorithmn;
import com.example.Employee_metadata.Entity.Employee;
import com.example.Employee_metadata.Entity.Tests;
import com.example.Employee_metadata.Enums.AlgorithmType;
import com.example.Employee_metadata.Repository.AlgorithmnRepository;
import com.example.Employee_metadata.Repository.EmployeeRepository;
import com.example.Employee_metadata.Repository.TestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor // Best practice: replaces @Autowired on individual fields
public class DataSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final AlgorithmnRepository algorithmnRepository;
    private final TestsRepository testsRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (employeeRepository.count() > 0)
            return; // Prevent duplicate seeding

        // 1. Create Employee (The Senior SDET)
        Employee employee = new Employee();
        employee.setName("Donov Rodgers");
        employee.setEmail("d.rodgers@algotest.io");
        employee.setUsername("donovr");
        employee.setPassword("securepassword123"); // In production, use hashed passwords!
        employee.setDepartment("Core Engineering");
        employee.setRole("Senior SDET");
        employeeRepository.save(employee);

        // 2. Create Algorithms
        Algorithmn acc = new Algorithmn();
        acc.setName("New LLamma v4");
        acc.setType(AlgorithmType.LARGE_LANGUAGE_MODEL);
        acc.setDescription("Large language model for natural language understanding.");
        acc.setSuccessRate(94.5);
        acc.setFailureRate(5.5);
        acc.setEmployee(employee);
        algorithmnRepository.save(acc);

        Algorithmn lka = new Algorithmn();
        lka.setName("Lane Keep Assist CNN");
        lka.setType(AlgorithmType.COMPUTER_VISION);
        lka.setDescription("CNN trained on 50,000 hours of highway lane footage.");
        lka.setSuccessRate(88.2);
        lka.setFailureRate(11.8);
        lka.setEmployee(employee);
        algorithmnRepository.save(lka);

        // 3. Create a Test with JSON Telemetry
        Tests testRun = new Tests();
        testRun.setName("Urban Collision Avoidance Test #102");
        testRun.setDescription("Testing ACC response to unexpected pedestrian crossing in CARLA.");
        testRun.setAlgorithmn(acc);

        // Mocking the JSON "events" map
        Map<String, Object> telemetryData = new HashMap<>();
        telemetryData.put("speed", 45.2);
        telemetryData.put("coordinates", Map.of("x", 124.5, "y", 88.2, "z", 0.0));
        telemetryData.put("status", "SUCCESS");
        telemetryData.put("timestamp", System.currentTimeMillis());

        testRun.setEvents(telemetryData);
        testsRepository.save(testRun);

        System.out.println("--- AlgoTest Data Seeding Complete ---");
    }
}
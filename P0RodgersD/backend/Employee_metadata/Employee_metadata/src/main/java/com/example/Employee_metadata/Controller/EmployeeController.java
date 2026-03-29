package com.example.Employee_metadata.Controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee_metadata.Dto.EmployeeDTO;
import com.example.Employee_metadata.Service.AlgorithmnService;
import com.example.Employee_metadata.Service.EmployeeService;
import com.example.Employee_metadata.Service.TestService;

@RestController
@RequestMapping("/api") // Base URL for all endpoints in this controller
@CrossOrigin(origins = "*") // Allow requests from the React frontend
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    // private final TestService testService;
    // private final AlgorithmnService algorithmnService;

    @GetMapping("/employee-metadata-username")
    public ResponseEntity<EmployeeDTO.EmployeeAlgoDTO> getEmployeeMetadataByUsername(
            @RequestParam("username") String username) {
        return ResponseEntity.ok(employeeService.getEmployeeMetadataByUsername(username));
    }

    @GetMapping("/employee-metadata-name")
    public ResponseEntity<EmployeeDTO.EmployeeAlgoDTO> getEmployeeMetadataByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(employeeService.getEmployeeMetadataByName(name));
    }

}

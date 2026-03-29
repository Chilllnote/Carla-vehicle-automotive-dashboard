package com.example.Employee_metadata.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Employee_metadata.Dto.AlgorithmnDTOs;
import com.example.Employee_metadata.Dto.EmployeeDTO;
import com.example.Employee_metadata.Dto.EmployeeDTO.EmployeeAlgoDTO;
import com.example.Employee_metadata.Dto.EmployeeDTO.EmployeeNoAlgoDTO;
import com.example.Employee_metadata.Entity.Algorithmn;
import com.example.Employee_metadata.Entity.Employee;
import com.example.Employee_metadata.Repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO.EmployeeAlgoDTO convertToDto(Employee employee) {
        return EmployeeDTO.EmployeeAlgoDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .username(employee.getUsername())
                .password(employee.getPassword())
                .department(employee.getDepartment())
                .role(employee.getRole())
                .algorithm(
                        employee.getAlgorithmns().stream()
                                .map(this::convertAlgorithmnToSummaryDto)
                                .toList())
                .build();
    }

    // Change 'AlgorithmnSummaryDTOs' to 'AlgorithmnDTOs'
    private AlgorithmnDTOs.AlgorithmSummaryDTO convertAlgorithmnToSummaryDto(Algorithmn algo) {
        return new AlgorithmnDTOs.AlgorithmSummaryDTO(
                algo.getId(),
                algo.getName(),
                algo.getType().name(),
                algo.getSuccessRate());
    }

    public EmployeeAlgoDTO getEmployeeMetadataByID(String id) {
        // Implementation to retrieve employee metadata by ID
        return employeeRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public EmployeeAlgoDTO getEmployeeMetadataByName(String name) {
        // Implementation to retrieve employee metadata by name
        return employeeRepository.findByName(name)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public EmployeeAlgoDTO getEmployeeMetadataByUsername(String username) {
        // Implementation to retrieve employee metadata by username
        return employeeRepository.findByUsername(username)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}

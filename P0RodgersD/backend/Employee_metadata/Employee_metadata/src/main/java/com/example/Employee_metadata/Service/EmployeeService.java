package com.example.Employee_metadata.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Employee_metadata.Dto.AlgorithmnDTOs;
import com.example.Employee_metadata.Dto.EmployeeDTO;
import com.example.Employee_metadata.Dto.EmployeeDTO.EmployeeAlgoDTO;
import com.example.Employee_metadata.Dto.EmployeeDTO.EmployeeNoAlgoDTO;
import com.example.Employee_metadata.Dto.NoteDTO;
import com.example.Employee_metadata.Entity.Algorithmn;
import com.example.Employee_metadata.Entity.Employee;
import com.example.Employee_metadata.Entity.Note;
import com.example.Employee_metadata.Repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

        @Autowired
        private EmployeeRepository employeeRepository;

        @Autowired
        private NoteService noteService;

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
                                .note(employee.getNotes() != null ? employee.getNotes().stream().map(
                                                note -> new NoteDTO.SentNoteDTO(
                                                                note.getId(),
                                                                note.getTitle(),
                                                                note.getDescription(),
                                                                note.getEmployee().getUsername(),
                                                                note.getCreatedAt()))
                                                .toList() : null)
                                .algorithm(employee.getAlgorithmns() != null ? employee.getAlgorithmns().stream()
                                                .map(this::convertAlgorithmnToSummaryDto)
                                                .toList() : null)
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

        public EmployeeAlgoDTO authenticateEmployee(String username, String password) {
                // Implementation to authenticate employee
                return employeeRepository.findByUsername(username)
                                .filter(emp -> emp.getPassword().equals(password)) // Simple password check (In
                                                                                   // production, use Spring
                                                                                   // Security/BCrypt)
                                .map(this::convertToDto)
                                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        }

        public EmployeeAlgoDTO addEmployee(EmployeeDTO.RecievedEmployeeDTO employeeNoAlgoDTO) {
                // Implementation to add a new employee
                Employee employee = Employee.builder()
                                .name(employeeNoAlgoDTO.getName())
                                .email(employeeNoAlgoDTO.getEmail())
                                .username(employeeNoAlgoDTO.getUsername())
                                .password(employeeNoAlgoDTO.getPassword())
                                .department(employeeNoAlgoDTO.getDepartment())
                                .role(employeeNoAlgoDTO.getRole())
                                .build();
                Employee savedEmployee = employeeRepository.save(employee);
                System.out.println("Saved Employee: " + savedEmployee); // Debugging statement
                return convertToDto(savedEmployee);
        }
}

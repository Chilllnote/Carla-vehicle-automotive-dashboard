package com.example.Employee_metadata.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;

import org.apache.commons.logging.impl.Slf4jLogFactory;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Employee_metadata.Dto.EmployeeDTO;
import com.example.Employee_metadata.Entity.Employee;
import com.example.Employee_metadata.Repository.EmployeeRepository;
import com.example.Employee_metadata.Service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;
    
    @InjectMocks
    EmployeeService employeeService;

    private EmployeeDTO.RecievedEmployeeDTO inputDto;
    private Employee savedRepoEmployee; // Class level

    @BeforeEach
    void setUp() {
        String fakeUuid = "a5d861b2-30cf-40e3-8f41-69e08bb371ec";

        inputDto = EmployeeDTO.RecievedEmployeeDTO.builder()
                .name("John Doe")
                .email("johndoe@example.com")
                .username("johndoe")
                .password("password123")
                .department("Engineering")
                .role("Developer")
                .build();

        // Assigning to the CLASS-LEVEL variable (no 'Employee' prefix)
        savedRepoEmployee = Employee.builder()
                .id(fakeUuid)
                .name("John Doe")
                .email("johndoe@example.com")
                .username("johndoe")
                .password("password123")
                .department("Engineering")
                .role("Developer")
                .notes(new ArrayList<>())
                .algorithmns(new ArrayList<>())
                .build();
    }

    @Test
    void testAddEmployee_Success() {
        // 1. ARRANGE: Mock the Repository ONLY
        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(savedRepoEmployee);

        // 2. ACT: Call the REAL service
        EmployeeDTO.EmployeeAlgoDTO result = employeeService.addEmployee(inputDto);

        // 3. ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals("a5d861b2-30cf-40e3-8f41-69e08bb371ec", result.getId());
        Assertions.assertEquals("John Doe", result.getName());
        Assertions.assertEquals("Engineering", result.getDepartment());
    }


    // @Test
    // void testAuthenticateEmployee() {

    // }

    // @Test
    // void testDeleteEmployeeById() {

    // }

    // @Test
    // void testGetEmployeeMetadataByID() {

    // employeeService.getEmployeeMetadataByID("f2d8cbfd-92b3-416b-849c-247a35bc4238");
    // }

    // @Test
    // void testGetEmployeeMetadataByName() {

    // }

    // @Test
    // void testGetEmployeeMetadataByUsername() {

    // }
}

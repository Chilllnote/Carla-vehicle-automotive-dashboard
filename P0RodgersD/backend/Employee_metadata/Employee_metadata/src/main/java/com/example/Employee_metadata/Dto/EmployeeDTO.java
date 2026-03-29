package com.example.Employee_metadata.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object for Employee.
 * Used for receiving input from React and sending clean responses.
 */

public class EmployeeDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeAlgoDTO {

        private String id;

        @NotBlank(message = "Name cannot be empty")
        private String name;

        @NotBlank(message = "Username cannot be empty")
        private String username;

        @NotBlank(message = "Password cannot be empty")
        private String password;

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        private String email;

        private String department;
        private String role;

        // Use a list of identifiers or basic types to avoid circular recursion
        private List<AlgorithmnDTOs.AlgorithmSummaryDTO> algorithm;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeNoAlgoDTO {

        private String id;

        @NotBlank(message = "Name cannot be empty")
        private String name;

        @NotBlank(message = "Username cannot be empty")
        private String username;

        @NotBlank(message = "Password cannot be empty")
        private String password;

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        private String email;

        private String department;
        private String role;

        // Use a list of identifiers or basic types to avoid circular recursion
        private List<AlgorithmnDTOs.AlgorithmSummaryDTO> algorithm;
    }

}
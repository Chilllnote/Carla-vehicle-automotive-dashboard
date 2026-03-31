package com.example.Employee_metadata.Dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class NoteDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecievedNoteDTO {

        @NotBlank(message = "ID cannot be empty")
        private String id;

        @NotBlank(message = "Name cannot be empty")
        private String title;

        @NotBlank(message = "Username cannot be empty")
        private String description;

        @NotBlank(message = "Email is required")
        private String username;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SentNoteDTO {

        @NotBlank(message = "ID cannot be empty")
        private String id;

        @NotBlank(message = "Name cannot be empty")
        private String title;

        @NotBlank(message = "Username cannot be empty")
        private String description;

        @NotBlank(message = "Email is required")
        private String username;

        @NotBlank(message = "Time of creation is required")
        private LocalDateTime createdAt;

    }

}

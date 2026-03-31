package com.example.Employee_metadata.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AlgorithmnDTOs {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AlgorithmSummaryDTO {
        private String id;
        private String name;
        private String type;
        private double successRate;
    }
}
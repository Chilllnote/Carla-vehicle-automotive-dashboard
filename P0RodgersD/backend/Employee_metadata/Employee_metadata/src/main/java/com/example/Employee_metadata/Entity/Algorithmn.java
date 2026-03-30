package com.example.Employee_metadata.Entity;

import java.util.List;

import com.example.Employee_metadata.Enums.AlgorithmType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Algorithmn {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private AlgorithmType type;

    private String name;
    private String description;
    private double successRate;
    private double failureRate;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @JsonIgnore
    @OneToMany(mappedBy = "algorithmn")
    private List<Tests> tests;

}

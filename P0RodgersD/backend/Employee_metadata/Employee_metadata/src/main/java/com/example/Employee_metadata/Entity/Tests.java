package com.example.Employee_metadata.Entity;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tests {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;

    // Connects this test to the Algorithm used
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "algorithm_id")
    private Algorithmn algorithmn;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "events", columnDefinition = "jsonb")
    private Map<String, Object> events = new HashMap<>();
}

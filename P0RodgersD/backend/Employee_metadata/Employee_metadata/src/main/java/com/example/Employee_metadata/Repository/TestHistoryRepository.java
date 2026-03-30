package com.example.Employee_metadata.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employee_metadata.Entity.TestHistory;

@Repository
public interface TestHistoryRepository extends JpaRepository<TestHistory, String> {
    // Spring Data JPA magic: Finds the 3 most recent tests for a specific employee
    List<TestHistory> findTop3ByEmployeeIdOrderByCreatedAtDesc(String employeeId);
}

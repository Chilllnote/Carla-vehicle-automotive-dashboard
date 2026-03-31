package com.example.Employee_metadata.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employee_metadata.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findById(String id);

    Optional<Employee> findByName(String name);

    Optional<List<Employee>> findByDepartment(String department);

    Optional<Employee> findByUsername(String username);

}

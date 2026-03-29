package com.example.Employee_metadata.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Employee_metadata.Entity.Algorithmn;
import com.example.Employee_metadata.Entity.Tests;

@Repository
public interface TestsRepository extends JpaRepository<Tests, Long> {

    Optional<Tests> findById(String id);

    // This fetches the actual Algorithm object linked to a specific Test ID
    @Query("SELECT t.algorithmn FROM Tests t WHERE t.id = :testId")
    Optional<Algorithmn> findAlgorithmnByTestId(@Param("testId") String testId);

}

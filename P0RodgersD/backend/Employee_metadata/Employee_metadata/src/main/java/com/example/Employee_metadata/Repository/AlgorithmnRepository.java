package com.example.Employee_metadata.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employee_metadata.Entity.Algorithmn;

@Repository
public interface AlgorithmnRepository extends JpaRepository<Algorithmn, Long> {

    Optional<Algorithmn> findById(String id);

    Optional<Algorithmn> findByName(String name);

    Optional<Algorithmn> findByType(String type);
}

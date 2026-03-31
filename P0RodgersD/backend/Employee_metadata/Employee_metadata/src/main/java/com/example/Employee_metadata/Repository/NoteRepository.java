package com.example.Employee_metadata.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employee_metadata.Entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {

    Optional<List<Note>> findByEmployee_Username(String username);
}

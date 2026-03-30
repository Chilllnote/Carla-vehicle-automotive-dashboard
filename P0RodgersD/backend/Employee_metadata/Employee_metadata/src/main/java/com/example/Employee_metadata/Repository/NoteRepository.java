package com.example.Employee_metadata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Employee_metadata.Entity.Note;

public interface NoteRepository extends JpaRepository<Note, String> {

}

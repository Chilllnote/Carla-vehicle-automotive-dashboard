package com.example.Employee_metadata.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Employee_metadata.Dto.NoteDTO;
import com.example.Employee_metadata.Entity.Note;
import com.example.Employee_metadata.Repository.EmployeeRepository;
import com.example.Employee_metadata.Repository.NoteRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private NoteDTO.RecievedNoteDTO convertToRecievedDto(NoteDTO.SentNoteDTO sentNoteDTO) {
        return NoteDTO.RecievedNoteDTO.builder()
                .id(sentNoteDTO.getId())
                .title(sentNoteDTO.getTitle())
                .description(sentNoteDTO.getDescription())
                .username(sentNoteDTO.getUsername())
                .build();
    }

    private Note convertToEntity(NoteDTO.RecievedNoteDTO recievedNote) {
        var note = new Note();
        // System.out.println("Recieved Note DTO: " + recievedNote); // Debugging
        // statement

        if (recievedNote.getId() != null && !recievedNote.getId().equals("UUID")) {
            note.setId(recievedNote.getId());
        }

        note.setTitle(recievedNote.getTitle());
        note.setDescription(recievedNote.getDescription());
        note.setEmployee(employeeRepository.findByUsername(recievedNote.getUsername())
                .orElseThrow(() -> new RuntimeException(
                        "Employee not found with username: " + recievedNote.getUsername())));
        // Employee association should be handled separately, as it requires fetching
        // the Employee entity
        return note;
    }

    public List<NoteDTO.SentNoteDTO> getNotesByEmployeeUsername(String username) {
        return noteRepository.findByEmployee_Username(username)
                .orElseThrow(() -> new RuntimeException("No notes found for employee with username: " + username))
                .stream()
                .map(note -> NoteDTO.SentNoteDTO.builder()
                        .title(note.getTitle())
                        .description(note.getDescription())
                        .username(note.getEmployee().getUsername())
                        .createdAt(note.getCreatedAt())
                        .build())
                .toList();
    }

    public void saveNote(NoteDTO.RecievedNoteDTO recievedNoteDTO) {
        // System.out.println("We got to the saveNote method with DTO: " +
        // recievedNoteDTO);

        var convertedNote = convertToEntity(recievedNoteDTO);
        // System.out.println("Converted user: " +
        // convertedNote.getEmployee().getUsername()); // Debugging statement

        var note = noteRepository.save(convertedNote);
        // System.out.println("Saved Note Entity: " + note); // Debugging statement
    }

}

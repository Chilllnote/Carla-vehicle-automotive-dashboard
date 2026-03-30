package com.example.Employee_metadata.Controller;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee_metadata.Dto.EmployeeDTO;
import com.example.Employee_metadata.Dto.NoteDTO;
import com.example.Employee_metadata.Entity.Employee;
import com.example.Employee_metadata.Repository.EmployeeRepository;
import com.example.Employee_metadata.Service.AlgorithmnService;
import com.example.Employee_metadata.Service.EmployeeService;
import com.example.Employee_metadata.Service.NoteService;
import com.example.Employee_metadata.Service.TestService;

@RestController
@RequestMapping("/api") // Base URL for all endpoints in this controller
@CrossOrigin(origins = "*") // Allow requests from the React frontend
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    // private final TestService testService;
    // private final AlgorithmnService algorithmnService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee-auth")
    public ResponseEntity<EmployeeDTO.EmployeeAlgoDTO> authenticateEmployee(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        return ResponseEntity.ok(employeeService.authenticateEmployee(username, password));
    }

    @GetMapping("/employee-metadata-username")
    public ResponseEntity<EmployeeDTO.EmployeeAlgoDTO> getEmployeeMetadataByUsername(
            @RequestParam("username") String username) {
        return ResponseEntity.ok(employeeService.getEmployeeMetadataByUsername(username));
    }

    @GetMapping("/employee-metadata-name")
    public ResponseEntity<EmployeeDTO.EmployeeAlgoDTO> getEmployeeMetadataByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(employeeService.getEmployeeMetadataByName(name));
    }

    @GetMapping("/get-employee-notes")
    public ResponseEntity<List<NoteDTO.SentNoteDTO>> getEmployeeNotes(@RequestParam("username") String username) {
        return ResponseEntity.ok(noteService.getNotesByEmployeeUsername(username));
    }

    @GetMapping("/get-notes-by-employee-repo-username")
    public ResponseEntity<List<NoteDTO.SentNoteDTO>> getNotesByEmployeeRepoUsername(
            @RequestParam("username") String username) {
        return ResponseEntity.ok(noteService.getNotesByEmployeeUsername(username));
    }

    @PostMapping("/add-note")
    public ResponseEntity<String> addNote(@RequestBody NoteDTO.RecievedNoteDTO recievedNoteDTO) {
        try {
            noteService.saveNote(recievedNoteDTO);
            System.out.println("Endpoint reached:" + recievedNoteDTO); // Debugging statement
            return ResponseEntity.ok("Note added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body(e.getMessage());
        }
    }

}

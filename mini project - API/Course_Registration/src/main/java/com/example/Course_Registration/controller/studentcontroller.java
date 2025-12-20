package com.example.Course_Registration.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Course_Registration.model.studentmodel;

import java.util.*;

@RestController
@RequestMapping("/students")
public class studentcontroller {

    private Map<Integer, studentmodel> studentMap = new HashMap<>();

    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody studentmodel student) {

        if (student.getName() == null || student.getName().trim().isEmpty() ||
            student.getCourse() == null || student.getCourse().trim().isEmpty()) {
            return new ResponseEntity<>("Name and course must not be empty",
                    HttpStatus.BAD_REQUEST);
        }

        if (studentMap.containsKey(student.getId())) {
            return new ResponseEntity<>("Student with this ID already exists",
                    HttpStatus.CONFLICT);
        }

        studentMap.put(student.getId(), student);
        return new ResponseEntity<>("Student registered successfully",
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<studentmodel>> getAllStudents() {
        return new ResponseEntity<>(
                new ArrayList<>(studentMap.values()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
    studentmodel student = studentMap.get(id);

        if (student == null) {
            return new ResponseEntity<>("Student not found with ID: " + id,
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        if (!studentMap.containsKey(id)) {
            return new ResponseEntity<>("Student not found with ID: " + id,
                    HttpStatus.NOT_FOUND);
        }

        studentMap.remove(id);
        return new ResponseEntity<>("Student deleted successfully",
                HttpStatus.OK);
    }
}

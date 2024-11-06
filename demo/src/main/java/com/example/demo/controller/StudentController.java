package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

     // Custom endpoint to find students by name
    @GetMapping("/byname/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return studentService.getStudentsByName(name);
    }

    // Custom endpoint to find students by age range
    @GetMapping("/byage/{minAge}/{maxAge}")
    public List<Student> getStudentsByAgeRange(@PathVariable int minAge, @PathVariable int maxAge) {
        return studentService.getStudentsByAgeRange(minAge, maxAge);
    }

    // Custom endpoint to find students whose name starts with a specific prefix
    @GetMapping("/byprefix/{prefix}")
    public List<Student> getStudentsByPrefix(@PathVariable String prefix) {
        return studentService.getStudentsByPrefix(prefix);
    }

}



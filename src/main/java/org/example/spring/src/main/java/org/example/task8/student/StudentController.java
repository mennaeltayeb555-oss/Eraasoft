package org.example.task8.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Create a student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Register a student to a course
    @PostMapping("/{studentId}/register/{courseId}")
    public Student registerStudentToCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return studentService.registerStudentToCourse(studentId, courseId);
    }
    @GetMapping("/{id}")
    public StudentDetailsDTO getStudentDetails(@PathVariable Long id) {
        return studentService.getStudentDetails(id);
    }
}

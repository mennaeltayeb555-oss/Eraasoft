package org.example.task10.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // API 3: get all students and teachers related to this student
    @GetMapping
    public List<StudentDetailsDTO> getAllStudentsWithTeachers() {
        return studentService.getAllStudentsWithTeachers();
    }

    // API 4: get student and teachers related to this student by id
    @GetMapping("/{id}")
    public StudentDetailsDTO getStudentWithTeachersById(@PathVariable Long id) {
        return studentService.getStudentWithTeachersById(id);
    }
}

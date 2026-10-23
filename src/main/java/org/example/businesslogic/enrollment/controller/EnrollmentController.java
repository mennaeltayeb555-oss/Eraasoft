package org.example.businesslogic.enrollment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.enrollment.dto.EnrollRequest;
import org.example.businesslogic.enrollment.model.Course;
import org.example.businesslogic.enrollment.model.Enrollment;
import org.example.businesslogic.enrollment.model.Student;
import org.example.businesslogic.enrollment.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.getEnrollments();
    }

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return enrollmentService.getCourses();
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return enrollmentService.getStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enrollment enroll(@Valid @RequestBody EnrollRequest request) {
        return enrollmentService.enroll(request);
    }

    @PutMapping("/{id}/drop")
    public Enrollment drop(@PathVariable Long id) {
        return enrollmentService.drop(id);
    }
}

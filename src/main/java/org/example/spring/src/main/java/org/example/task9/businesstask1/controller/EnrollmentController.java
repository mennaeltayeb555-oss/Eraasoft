package org.example.task9.businesstask1.controller;

import lombok.RequiredArgsConstructor;
import org.example.task9.businesstask1.service.EnrollmentService;
import org.example.task9.businesstask1.model.Enrollment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // POST /api/enrollments?studentId=1&courseId=2
    @PostMapping
    public Enrollment enrollStudent(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {

        return enrollmentService.enrollStudent(
                studentId,
                courseId
        );
    }

    @PutMapping("/{id}/drop")
    public Enrollment dropCourse(@PathVariable Long id) {
        return enrollmentService.dropCourse(id);
    }

    @PutMapping("/{id}/complete")
    public Enrollment markAsCompleted(@PathVariable Long id) {
        return enrollmentService.markAsCompleted(id);
    }

    @PutMapping("/{id}/fail")
    public Enrollment markAsFailed(@PathVariable Long id) {
        return enrollmentService.markAsFailed(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getStudentEnrollments(
            @PathVariable Long studentId) {

        return enrollmentService.getStudentEnrollments(studentId);
    }
}
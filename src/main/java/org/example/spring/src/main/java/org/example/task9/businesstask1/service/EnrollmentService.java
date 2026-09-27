package org.example.task9.businesstask1.service;

import org.example.task9.businesstask1.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment enrollStudent(Long studentId, Long courseId);

    Enrollment dropCourse(Long enrollmentId);

    Enrollment markAsCompleted(Long enrollmentId);

    Enrollment markAsFailed(Long enrollmentId);

    List<Enrollment> getStudentEnrollments(Long studentId);
}
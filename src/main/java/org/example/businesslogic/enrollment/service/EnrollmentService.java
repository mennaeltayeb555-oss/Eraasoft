package org.example.businesslogic.enrollment.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.example.businesslogic.enrollment.dto.EnrollRequest;
import org.example.businesslogic.enrollment.model.Course;
import org.example.businesslogic.enrollment.model.Enrollment;
import org.example.businesslogic.enrollment.model.EnrollmentStatus;
import org.example.businesslogic.enrollment.model.Student;
import org.example.businesslogic.enrollment.repository.CourseRepository;
import org.example.businesslogic.enrollment.repository.EnrollmentRepository;
import org.example.businesslogic.enrollment.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public List<Enrollment> getEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public synchronized Enrollment enroll(EnrollRequest request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new NotFoundException("Student not found"));
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new NotFoundException("Course not found"));

        if (enrollmentRepository.isStudentEnrolled(student.getId(), course.getId())) {
            throw new BusinessException("Student is already enrolled in " + course.getCode());
        }
        if (!course.getAllowedLevels().contains(student.getLevel())) {
            throw new BusinessException(course.getCode() + " is only for " + course.getAllowedLevels());
        }

        checkPrerequisites(student, course);

        if (course.isFull()) {
            throw new BusinessException(course.getCode() + " is full");
        }

        course.setEnrolledStudents(course.getEnrolledStudents() + 1);
        courseRepository.save(course);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(student.getId());
        enrollment.setCourseId(course.getId());
        enrollment.setStatus(EnrollmentStatus.ENROLLED);
        enrollment.setEnrolledAt(LocalDateTime.now());
        return enrollmentRepository.save(enrollment);
    }

    public synchronized Enrollment drop(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NotFoundException("Enrollment not found"));

        if (enrollment.getStatus() == EnrollmentStatus.DROPPED) {
            throw new BusinessException("Course is already dropped");
        }

        Course course = courseRepository.findById(enrollment.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course not found"));

        if (LocalDate.now().isAfter(course.getDropDeadline())) {
            throw new BusinessException("Drop deadline for " + course.getCode() + " was " + course.getDropDeadline());
        }

        // free the seat for another student
        course.setEnrolledStudents(course.getEnrolledStudents() - 1);
        courseRepository.save(course);

        enrollment.setStatus(EnrollmentStatus.DROPPED);
        enrollment.setDroppedAt(LocalDateTime.now());
        return enrollmentRepository.save(enrollment);
    }

    private void checkPrerequisites(Student student, Course course) {
        for (Long prerequisiteId : course.getPrerequisiteIds()) {
            Course prerequisite = courseRepository.findById(prerequisiteId)
                    .orElseThrow(() -> new NotFoundException("Prerequisite course not found"));
            Boolean passed = student.getCourseResults().get(prerequisiteId);

            if (passed == null) {
                throw new BusinessException("Student must finish " + prerequisite.getCode() + " first");
            }
            if (!passed) {
                throw new BusinessException("Student failed " + prerequisite.getCode() + " which is required for " + course.getCode());
            }
        }
    }
}

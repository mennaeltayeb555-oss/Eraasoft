package org.example.businesslogic.enrollment.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.enrollment.model.Enrollment;
import org.example.businesslogic.enrollment.model.EnrollmentStatus;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepository extends InMemoryRepository<Enrollment> {

    public boolean isStudentEnrolled(Long studentId, Long courseId) {
        return findAll().stream()
                .anyMatch(e -> e.getStudentId().equals(studentId)
                        && e.getCourseId().equals(courseId)
                        && e.getStatus() == EnrollmentStatus.ENROLLED);
    }
}

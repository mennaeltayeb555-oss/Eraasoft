package org.example.task9.businesstask1.repository;

import org.example.task9.businesstask1.enrollment.EnrollmentStatus;
import org.example.task9.businesstask1.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // كل تسجيلات طالب معين
    List<Enrollment> findByStudentId(Long studentId);

    // كل تسجيلات كورس معين
    List<Enrollment> findByCourseId(Long courseId);

    // تسجيل طالب في كورس معين
    List<Enrollment> findByStudentIdAndCourseId(
            Long studentId,
            Long courseId
    );

    // عدد الطلاب المسجلين حاليًا في الكورس
    long countByCourseIdAndStatus(
            Long courseId,
            EnrollmentStatus status
    );
}

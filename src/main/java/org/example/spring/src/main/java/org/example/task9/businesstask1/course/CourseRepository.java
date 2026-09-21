package org.example.task9.businesstask1.course;

import org.example.task9.businesstask1.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
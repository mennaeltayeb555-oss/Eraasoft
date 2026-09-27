package org.example.task9.businesstask1.repository;

import org.example.task9.businesstask1.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
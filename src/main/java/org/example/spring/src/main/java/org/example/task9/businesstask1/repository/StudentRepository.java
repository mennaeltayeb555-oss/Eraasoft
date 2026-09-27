package org.example.task9.businesstask1.repository;

import org.example.task9.businesstask1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

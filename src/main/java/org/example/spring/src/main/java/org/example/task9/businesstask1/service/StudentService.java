package org.example.task9.businesstask1.service;

import org.example.task9.businesstask1.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);
}

package org.example.task8.service;

import org.example.task8.dto.studentdto.StudentDetailsDTO;
import org.example.task8.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student registerStudentToCourse(Long studentId, Long courseId);

    StudentDetailsDTO getStudentDetails(Long id);
}
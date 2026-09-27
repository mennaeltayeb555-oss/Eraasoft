package org.example.task10.service;

import org.example.task10.dto.studentdto.StudentDetailsDTO;
import org.example.task10.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<StudentDetailsDTO> getAllStudentsWithTeachers();

    StudentDetailsDTO getStudentWithTeachersById(Long id);
}
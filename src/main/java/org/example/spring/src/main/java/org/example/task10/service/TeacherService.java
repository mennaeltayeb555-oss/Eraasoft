package org.example.task10.service;

import org.example.task10.dto.techerdto.TeacherDetailsDTO;
import org.example.task10.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher createTeacher(Teacher teacher);

    Teacher linkStudent(Long teacherId, Long studentId);

    List<TeacherDetailsDTO> getAllTeachersWithStudents();

    TeacherDetailsDTO getTeacherWithStudentsById(Long id);
}
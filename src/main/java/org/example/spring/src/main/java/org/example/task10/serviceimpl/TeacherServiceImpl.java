package org.example.task10.serviceimpl;


import lombok.RequiredArgsConstructor;
import org.example.task10.dto.techerdto.StudentInfoDTO;
import org.example.task10.dto.techerdto.TeacherDetailsDTO;
import org.example.task10.exeption.ResourceNotFoundException;
import org.example.task10.model.Student;
import org.example.task10.model.Teacher;
import org.example.task10.service.TeacherService;
import org.example.task10.repository.StudentRepository;
import org.example.task10.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // ربط طالب بمدرّس (عشان نقدر نجرب الـ APIs المطلوبة)
    @Override
    public Teacher linkStudent(Long teacherId, Long studentId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        teacher.getStudents().add(student);
        return teacherRepository.save(teacher);
    }

    // === API 1: كل المدرّسين + طلبتهم ===
    @Override
    public List<TeacherDetailsDTO> getAllTeachersWithStudents() {
        return teacherRepository.findAll().stream()
                .map(this::toDetailsDTO)
                .toList();
    }

    // === API 2: مدرّس واحد بالـ id + طلبته ===
    @Override
    public TeacherDetailsDTO getTeacherWithStudentsById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
        return toDetailsDTO(teacher);
    }

    private TeacherDetailsDTO toDetailsDTO(Teacher teacher) {
        List<StudentInfoDTO> studentDTOs = teacher.getStudents().stream()
                .map(s -> new StudentInfoDTO(s.getId(), s.getName(), s.getEmail()))
                .toList();

        return new TeacherDetailsDTO(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                studentDTOs
        );
    }
}
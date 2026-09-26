package org.example.task10.teacher;

import org.example.task10.student.Student;
import org.example.task10.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // ربط طالب بمدرّس (عشان نقدر نجرب الـ APIs المطلوبة)
    public Teacher linkStudent(Long teacherId, Long studentId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        teacher.getStudents().add(student);
        return teacherRepository.save(teacher);
    }

    // === API 1: كل المدرّسين + طلبتهم ===
    public List<TeacherDetailsDTO> getAllTeachersWithStudents() {
        return teacherRepository.findAll().stream()
                .map(this::toDetailsDTO)
                .toList();
    }

    // === API 2: مدرّس واحد بالـ id + طلبته ===
    public TeacherDetailsDTO getTeacherWithStudentsById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
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

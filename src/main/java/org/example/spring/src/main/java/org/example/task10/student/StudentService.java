package org.example.task10.student;

import org.example.task10.teacher.Teacher;
import org.example.task10.teacher.TeacherDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // === API 3: كل الطلبة + مدرّسيهم ===
    public List<StudentDetailsDTO> getAllStudentsWithTeachers() {
        return studentRepository.findAll().stream()
                .map(this::toDetailsDTO)
                .toList();
    }

    // === API 4: طالب واحد بالـ id + مدرّسينه ===
    public StudentDetailsDTO getStudentWithTeachersById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return toDetailsDTO(student);
    }

    private StudentDetailsDTO toDetailsDTO(Student student) {
        List<TeacherInfoDTO> teacherDTOs = student.getTeachers().stream()
                .map(t -> new TeacherInfoDTO(t.getId(), t.getName(), t.getEmail()))
                .toList();

        return new StudentDetailsDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                teacherDTOs
        );
    }
}

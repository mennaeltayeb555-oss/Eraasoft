package org.example.task10.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.task10.dto.studentdto.StudentDetailsDTO;
import org.example.task10.dto.studentdto.TeacherInfoDTO;
import org.example.task10.exeption.ResourceNotFoundException;
import org.example.task10.model.Student;
import org.example.task10.service.StudentService;
import org.example.task10.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // === API 3: كل الطلبة + مدرّسيهم ===
    @Override
    public List<StudentDetailsDTO> getAllStudentsWithTeachers() {
        return studentRepository.findAll().stream()
                .map(this::toDetailsDTO)
                .toList();
    }

    // === API 4: طالب واحد بالـ id + مدرّسينه ===
    @Override
    public StudentDetailsDTO getStudentWithTeachersById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
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

package org.example.task9.businesstask1.serviceimpl;


import lombok.RequiredArgsConstructor;
import org.example.task9.businesstask1.exeption.ResourceNotFoundException;
import org.example.task9.businesstask1.model.Student;
import org.example.task9.businesstask1.service.StudentService;
import org.example.task9.businesstask1.repository.StudentRepository;
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

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));
    }
}

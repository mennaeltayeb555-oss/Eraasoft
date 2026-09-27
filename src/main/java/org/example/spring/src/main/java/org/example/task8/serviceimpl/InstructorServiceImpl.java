package org.example.task8.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.task8.dto.instructordto.CourseInfoDTO;
import org.example.task8.dto.instructordto.InstructorDetailsDTO;
import org.example.task8.dto.instructordto.StudentInfoDTO;
import org.example.task8.exeption.ResourceNotFoundException;
import org.example.task8.model.Instructor;
import org.example.task8.service.InstructorService;
import org.example.task8.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    // Create an instructor
    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // Get all instructors
    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // Get instructor by id
    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + id));
    }

    @Override
    public InstructorDetailsDTO getInstructorDetails(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + id));

        List<CourseInfoDTO> courseDTOs = instructor.getCourses().stream()
                .map(course -> {
                    List<StudentInfoDTO> studentDTOs = course.getStudents().stream()
                            .map(student -> new StudentInfoDTO(
                                    student.getId(),
                                    student.getName(),
                                    student.getEmail()
                            ))
                            .toList();

                    return new CourseInfoDTO(
                            course.getId(),
                            course.getTitle(),
                            course.getDescription(),
                            studentDTOs
                    );
                })
                .toList();

        return new InstructorDetailsDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getEmail(),
                courseDTOs
        );
    }
}

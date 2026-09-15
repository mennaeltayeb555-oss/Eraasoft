package org.example.task8.instructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    // Create an instructor
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // Get all instructors
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // Get instructor by id (هنستخدمها جوه get instructor details)
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
    }
    public InstructorDetailsDTO getInstructorDetails(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));

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

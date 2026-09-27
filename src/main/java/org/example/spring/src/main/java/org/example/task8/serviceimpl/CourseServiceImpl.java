package org.example.task8.serviceimpl;


import org.example.task8.dto.coursedto.CourseDetailsDTO;
import org.example.task8.dto.coursedto.InstructorInfoDTO;
import org.example.task8.dto.coursedto.StudentInfoDTO;
import org.example.task8.model.Course;
import org.example.task8.repository.CourseRepository;
import org.example.task8.service.CourseService;
import org.example.task8.model.Instructor;
import org.example.task8.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    // Create a course
    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Get all courses
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get course by id
    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    // Assign an instructor to a course
    @Override
    public Course assignInstructorToCourse(Long courseId, Long instructorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + instructorId));

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public CourseDetailsDTO getCourseDetails(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        InstructorInfoDTO instructorDTO = null;
        if (course.getInstructor() != null) {
            instructorDTO = new InstructorInfoDTO(
                    course.getInstructor().getId(),
                    course.getInstructor().getName(),
                    course.getInstructor().getEmail()
            );
        }

        List<StudentInfoDTO> studentDTOs = course.getStudents().stream()
                .map(student -> new StudentInfoDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail()
                ))
                .toList();

        return new CourseDetailsDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                instructorDTO,
                studentDTOs
        );
    }
}

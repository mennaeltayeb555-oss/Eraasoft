package org.example.task8.student;

import org.example.task8.course.Course;
import org.example.task8.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Create a student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by id
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Register a student to a course
    public Student registerStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        student.getCourses().add(course);
        return studentRepository.save(student);
    }
    //DTO impl

    public StudentDetailsDTO getStudentDetails(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        List<CourseInfoDTO> courseDTOs = student.getCourses().stream()
                .map(course -> {
                    InstructorInfoDTO instructorDTO = null;
                    if (course.getInstructor() != null) {
                        instructorDTO = new InstructorInfoDTO(
                                course.getInstructor().getId(),
                                course.getInstructor().getName(),
                                course.getInstructor().getEmail()
                        );
                    }
                    return new CourseInfoDTO(
                            course.getId(),
                            course.getTitle(),
                            course.getDescription(),
                            instructorDTO
                    );
                })
                .toList();

        return new StudentDetailsDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                courseDTOs
        );
    }
}
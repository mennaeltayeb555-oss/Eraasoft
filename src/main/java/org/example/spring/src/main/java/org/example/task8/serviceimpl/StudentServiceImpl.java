package org.example.task8.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.task8.dto.studentdto.CourseInfoDTO;
import org.example.task8.dto.studentdto.InstructorInfoDTO;
import org.example.task8.dto.studentdto.StudentDetailsDTO;
import org.example.task8.exeption.ResourceNotFoundException;
import org.example.task8.model.Course;
import org.example.task8.service.StudentService;
import org.example.task8.model.Student;
import org.example.task8.repository.CourseRepository;
import org.example.task8.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // Create a student
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by id
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    // Register a student to a course
    @Override
    public Student registerStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        // الحفظ لازم يحصل من ناحية Course لأنه هو "المالك" في العلاقة
        course.getStudents().add(student);
        courseRepository.save(course);

        return student;
    }

    @Override
    public StudentDetailsDTO getStudentDetails(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

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

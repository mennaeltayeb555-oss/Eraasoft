package org.example.task9.businesstask1.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.example.task9.businesstask1.exeption.ResourceNotFoundException;
import org.example.task9.businesstask1.model.Course;
import org.example.task9.businesstask1.service.CourseService;
import org.example.task9.businesstask1.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + id));
    }

    // ضيف كورس كـ prerequisite لكورس تاني
    @Override
    @Transactional
    public Course addPrerequisite(Long courseId, Long prerequisiteId) {

        if (courseId.equals(prerequisiteId)) {
            throw new IllegalArgumentException(
                    "A course cannot be a prerequisite for itself");
        }

        Course course = getCourseById(courseId);
        Course prerequisite = getCourseById(prerequisiteId);

        if (course.getPrerequisites().contains(prerequisite)) {
            throw new IllegalArgumentException(
                    "This prerequisite is already added to the course");
        }

        course.getPrerequisites().add(prerequisite);

        return courseRepository.save(course);
    }
}
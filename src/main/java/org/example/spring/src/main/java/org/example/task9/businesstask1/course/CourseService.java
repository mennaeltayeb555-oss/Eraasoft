package org.example.task9.businesstask1.course;

import lombok.RequiredArgsConstructor;
import org.example.task9.businesstask1.course.Course;
import org.example.task9.businesstask1.course.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Course not found with id: " + id));
    }

    // ضيف كورس كـ prerequisite لكورس تاني
    @Transactional
    public Course addPrerequisite(Long courseId, Long prerequisiteId) {

        if (courseId.equals(prerequisiteId)) {
            throw new RuntimeException(
                    "A course cannot be a prerequisite for itself");
        }

        Course course = getCourseById(courseId);
        Course prerequisite = getCourseById(prerequisiteId);

        if (course.getPrerequisites().contains(prerequisite)) {
            throw new RuntimeException(
                    "This prerequisite is already added to the course");
        }

        course.getPrerequisites().add(prerequisite);

        return courseRepository.save(course);
    }
}
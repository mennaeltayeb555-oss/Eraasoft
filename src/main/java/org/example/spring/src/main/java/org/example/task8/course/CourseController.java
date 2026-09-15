package org.example.task8.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Create a course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Assign an instructor to a course
    @PostMapping("/{courseId}/assign/{instructorId}")
    public Course assignInstructorToCourse(
            @PathVariable Long courseId,
            @PathVariable Long instructorId) {
        return courseService.assignInstructorToCourse(courseId, instructorId);
    }

    @GetMapping("/{id}")
    public CourseDetailsDTO getCourseDetails(@PathVariable Long id) {
        return courseService.getCourseDetails(id);
    }
}

package org.example.task9.businesstask1.course;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // POST /api/courses/2/prerequisites/1
    // الكورس 1 بقى شرط للكورس 2
    @PostMapping("/{courseId}/prerequisites/{prerequisiteId}")
    public Course addPrerequisite(
            @PathVariable Long courseId,
            @PathVariable Long prerequisiteId) {

        return courseService.addPrerequisite(
                courseId,
                prerequisiteId
        );
    }
}
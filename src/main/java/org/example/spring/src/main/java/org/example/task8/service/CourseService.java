package org.example.task8.service;

import org.example.task8.dto.coursedto.CourseDetailsDTO;
import org.example.task8.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course assignInstructorToCourse(Long courseId, Long instructorId);

    CourseDetailsDTO getCourseDetails(Long id);
}

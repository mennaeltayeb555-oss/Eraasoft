package org.example.task9.businesstask1.service;

import lombok.RequiredArgsConstructor;
import org.example.task9.businesstask1.repository.CourseRepository;
import org.example.task9.businesstask1.model.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.example.task9.businesstask1.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course addPrerequisite(Long courseId, Long prerequisiteId);
}
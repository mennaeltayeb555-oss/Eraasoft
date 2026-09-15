package org.example.task8.student;

import java.util.List;

public class StudentDetailsDTO {
    private Long id;
    private String name;
    private String email;
    private List<CourseInfoDTO> courses;

    public StudentDetailsDTO(Long id, String name, String email, List<CourseInfoDTO> courses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = courses;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<CourseInfoDTO> getCourses() { return courses; }
}
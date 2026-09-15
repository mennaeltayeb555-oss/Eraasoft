package org.example.task8.instructor;

import java.util.List;

public class InstructorDetailsDTO {
    private Long id;
    private String name;
    private String email;
    private List<CourseInfoDTO> courses;

    public InstructorDetailsDTO(Long id, String name, String email, List<CourseInfoDTO> courses) {
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

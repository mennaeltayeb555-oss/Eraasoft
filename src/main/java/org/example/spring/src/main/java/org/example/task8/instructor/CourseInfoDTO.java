package org.example.task8.instructor;

import java.util.List;

public class CourseInfoDTO {
    private Long id;
    private String title;
    private String description;
    private List<StudentInfoDTO> students;

    public CourseInfoDTO(Long id, String title, String description, List<StudentInfoDTO> students) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.students = students;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<StudentInfoDTO> getStudents() { return students; }
}

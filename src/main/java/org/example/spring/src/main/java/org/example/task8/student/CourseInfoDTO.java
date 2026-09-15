package org.example.task8.student;

public class CourseInfoDTO {
    private Long id;
    private String title;
    private String description;
    private InstructorInfoDTO instructor;

    public CourseInfoDTO(Long id, String title, String description, InstructorInfoDTO instructor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public InstructorInfoDTO getInstructor() { return instructor; }
}

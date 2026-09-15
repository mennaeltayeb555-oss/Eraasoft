package org.example.task8.course;

import java.util.List;

public class CourseDetailsDTO {
    private Long id;
    private String title;
    private String description;
    private InstructorInfoDTO instructor;
    private List<StudentInfoDTO> students;

    public CourseDetailsDTO(Long id, String title, String description,
                            InstructorInfoDTO instructor, List<StudentInfoDTO> students) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.students = students;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public InstructorInfoDTO getInstructor() { return instructor; }
    public List<StudentInfoDTO> getStudents() { return students; }
}

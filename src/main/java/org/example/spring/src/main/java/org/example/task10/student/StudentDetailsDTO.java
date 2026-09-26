package org.example.task10.student;

import java.util.List;

public class StudentDetailsDTO {
    private Long id;
    private String name;
    private String email;
    private List<TeacherInfoDTO> teachers;

    public StudentDetailsDTO(Long id, String name, String email, List<TeacherInfoDTO> teachers) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.teachers = teachers;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<TeacherInfoDTO> getTeachers() { return teachers; }
}

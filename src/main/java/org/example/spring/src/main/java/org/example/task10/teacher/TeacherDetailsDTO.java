package org.example.task10.teacher;

import java.util.List;

public class TeacherDetailsDTO {
    private Long id;
    private String name;
    private String email;
    private List<StudentInfoDTO> students;

    public TeacherDetailsDTO(Long id, String name, String email, List<StudentInfoDTO> students) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.students = students;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<StudentInfoDTO> getStudents() { return students; }
}

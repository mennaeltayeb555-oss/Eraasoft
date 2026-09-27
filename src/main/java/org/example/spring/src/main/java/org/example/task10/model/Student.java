package org.example.task10.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String email;

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private List<Teacher> teachers;

    public Student() {}

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Teacher> getTeachers() { return teachers; }
    public void setTeachers(List<Teacher> teachers) { this.teachers = teachers; }
}

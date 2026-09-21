package org.example.task9.businesstask1.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer maxStudents;

    // المستوى المطلوب عشان تسجلي في الكورس ده
    private Integer requiredLevel;

    // آخر يوم ينفع فيه drop للكورس
    private LocalDate dropDeadline;

    // الكورسات اللي لازم تتاخد قبل الكورس ده
    @ManyToMany
    @JoinTable(
            name = "course_prerequisites",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private List<Course> prerequisites;

    public Course(String title, Integer maxStudents,
                  Integer requiredLevel, LocalDate dropDeadline) {
        this.title = title;
        this.maxStudents = maxStudents;
        this.requiredLevel = requiredLevel;
        this.dropDeadline = dropDeadline;
    }
}
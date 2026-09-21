package org.example.task9.businesstask1.enrollment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import org.example.task9.businesstask1.course.Course;
import org.example.task9.businesstask1.student.Student;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    private LocalDate enrollDate;
    private LocalDate dropDate;

    public Enrollment(Student student, Course course,
                      EnrollmentStatus status, LocalDate enrollDate) {
        this.student = student;
        this.course = course;
        this.status = status;
        this.enrollDate = enrollDate;
    }
}
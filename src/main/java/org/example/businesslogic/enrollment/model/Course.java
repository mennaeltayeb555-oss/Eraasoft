package org.example.businesslogic.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course implements BaseEntity {

    private Long id;
    private String code;
    private String title;
    private int maxStudents;
    private int enrolledStudents;
    private List<Long> prerequisiteIds;
    private Set<StudentLevel> allowedLevels;
    private LocalDate dropDeadline;

    public boolean isFull() {
        return enrolledStudents >= maxStudents;
    }
}

package org.example.businesslogic.enrollment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Enrollment implements BaseEntity {

    private Long id;
    private Long studentId;
    private Long courseId;
    private EnrollmentStatus status;
    private LocalDateTime enrolledAt;
    private LocalDateTime droppedAt;
}

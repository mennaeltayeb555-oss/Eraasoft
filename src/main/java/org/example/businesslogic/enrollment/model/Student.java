package org.example.businesslogic.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements BaseEntity {

    private Long id;
    private String name;
    private StudentLevel level;

    // courseId -> true if the student passed it, false if failed
    private Map<Long, Boolean> courseResults;
}

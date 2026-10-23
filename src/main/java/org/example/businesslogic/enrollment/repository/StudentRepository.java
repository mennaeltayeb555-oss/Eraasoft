package org.example.businesslogic.enrollment.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.enrollment.model.Student;
import org.example.businesslogic.enrollment.model.StudentLevel;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StudentRepository extends InMemoryRepository<Student> {

    public StudentRepository() {
        save(new Student(null, "Hana Sherif", StudentLevel.LEVEL_1, Map.of()));
        save(new Student(null, "Mahmoud Reda", StudentLevel.LEVEL_2, Map.of(1L, true)));
        save(new Student(null, "Laila Emad", StudentLevel.LEVEL_3, Map.of(1L, true, 2L, false)));
        save(new Student(null, "Tarek Zaki", StudentLevel.LEVEL_3, Map.of(1L, true, 2L, true)));
    }
}

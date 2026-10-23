package org.example.businesslogic.enrollment.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.enrollment.model.Course;
import org.example.businesslogic.enrollment.model.StudentLevel;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public class CourseRepository extends InMemoryRepository<Course> {

    public CourseRepository() {
        Set<StudentLevel> allLevels = Set.of(StudentLevel.values());
        LocalDate nextMonth = LocalDate.now().plusMonths(1);

        save(new Course(null, "CS101", "Introduction to Programming", 3, 0, List.of(), allLevels, nextMonth));
        save(new Course(null, "CS201", "Data Structures", 2, 0, List.of(1L),
                Set.of(StudentLevel.LEVEL_2, StudentLevel.LEVEL_3, StudentLevel.LEVEL_4), nextMonth));
        save(new Course(null, "CS301", "Algorithms", 30, 0, List.of(2L),
                Set.of(StudentLevel.LEVEL_3, StudentLevel.LEVEL_4), nextMonth));
        // drop deadline already passed for this course
        save(new Course(null, "CS150", "Web Basics", 25, 0, List.of(), allLevels, LocalDate.now().minusDays(1)));
    }
}

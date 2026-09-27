package org.example.task9.task2.repository;


import org.example.task9.task2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameIn(List<String> names);
}

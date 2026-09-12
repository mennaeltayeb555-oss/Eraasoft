package org.example.task7.Employee;

import org.example.task7.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // 1- Derived query method (function name)
    List<Employee> findByNameStartingWith(String name);

    // 2- Non-native query (JPQL)
    @Query("SELECT e FROM Employee e WHERE e.name LIKE :name")
    List<Employee> searchByNameJPQL(@Param("name") String name);

    // 3- Native query
    @Query(value = "SELECT * FROM employees WHERE name LIKE :name", nativeQuery = true)
    List<Employee> searchByNameNative(@Param("name") String name);
}

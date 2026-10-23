package org.example.businesslogic.leave.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.leave.model.Employee;
import org.example.businesslogic.leave.model.EmployeeType;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository extends InMemoryRepository<Employee> {

    public EmployeeRepository() {
        save(new Employee("Ahmed Fathy", EmployeeType.FULL_TIME));
        save(new Employee("Salma Nabil", EmployeeType.PART_TIME));
        save(new Employee("Waleed Saad", EmployeeType.MANAGER));
    }
}

package org.example.task7.service;
import org.example.task7.model.Employee;

import java.util.List;
public interface EmployeeService {

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByIds(List<Long> ids);

    Employee saveEmployee(Employee employee);

    List<Employee> saveEmployees(List<Employee> employees);

    Employee updateEmployee(Employee employee);

    List<Employee> updateEmployees(List<Employee> employees);

    void deleteAllEmployees();

    void deleteEmployeeById(Long id);

    void deleteEmployeesByIds(List<Long> ids);

    List<Employee> searchByNameFunction(String name);

    List<Employee> searchByNameJPQL(String name);

    List<Employee> searchByNameNative(String name);
}

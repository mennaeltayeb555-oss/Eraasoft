package org.example.task7.serviceimp;


import org.example.task7.model.Employee;
import org.example.task7.repository.EmployeeRepository;
import org.example.task7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // get all employee
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // get all employee by List of ids
    @Override
    public List<Employee> getEmployeesByIds(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    // save employee
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // save List of employee
    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    // update employee
    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // update List of employee
    @Override
    public List<Employee> updateEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    // delete all employee
    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    // delete employee by ID
    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    // delete employee by List of ID
    @Override
    public void deleteEmployeesByIds(List<Long> ids) {
        employeeRepository.deleteAllById(ids);
    }

    // search by name - function name
    @Override
    public List<Employee> searchByNameFunction(String name) {
        return employeeRepository.findByNameStartingWith(name);
    }

    // search by name - JPQL
    @Override
    public List<Employee> searchByNameJPQL(String name) {
        return employeeRepository.searchByNameJPQL(name + "%");
    }

    // search by name - native query
    @Override
    public List<Employee> searchByNameNative(String name) {
        return employeeRepository.searchByNameNative(name + "%");
    }
}

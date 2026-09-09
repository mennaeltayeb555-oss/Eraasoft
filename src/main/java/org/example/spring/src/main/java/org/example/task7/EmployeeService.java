package org.example.task7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 @Service
    public class EmployeeService {

        @Autowired
        private EmployeeRepository employeeRepository;

        // get all employee
        public List<Employee> getAllEmployees() {
            return employeeRepository.findAll();
        }

        // get all employee by List of ids
        public List<Employee> getEmployeesByIds(List<Long> ids) {
            return employeeRepository.findAllById(ids);
        }

        // save employee
        public Employee saveEmployee(Employee employee) {
            return employeeRepository.save(employee);
        }

        // save List of employee
        public List<Employee> saveEmployees(List<Employee> employees) {
            return employeeRepository.saveAll(employees);
        }

        // update employee
        public Employee updateEmployee(Employee employee) {
            return employeeRepository.save(employee);
        }

        // update List of employee
        public List<Employee> updateEmployees(List<Employee> employees) {
            return employeeRepository.saveAll(employees);
        }

        // delete all employee
        public void deleteAllEmployees() {
            employeeRepository.deleteAll();
        }

        // delete employee by ID
        public void deleteEmployeeById(Long id) {
            employeeRepository.deleteById(id);
        }

        // delete employee by List of ID
        public void deleteEmployeesByIds(List<Long> ids) {
            employeeRepository.deleteAllById(ids);
        }

        // search by name - function name
        public List<Employee> searchByNameFunction(String name) {
            return employeeRepository.findByNameStartingWith(name);
        }

        // search by name - JPQL
        public List<Employee> searchByNameJPQL(String name) {
            return employeeRepository.searchByNameJPQL(name + "%");
        }

        // search by name - native query
        public List<Employee> searchByNameNative(String name) {
            return employeeRepository.searchByNameNative(name + "%");
        }
    }

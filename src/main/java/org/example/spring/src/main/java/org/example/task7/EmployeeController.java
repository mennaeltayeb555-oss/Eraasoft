package org.example.task7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // get employees by List of ids
    @GetMapping("/byIds")
    public List<Employee> getEmployeesByIds(@RequestParam List<Long> ids) {
        return employeeService.getEmployeesByIds(ids);
    }

    // save one employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // save List of employees
    @PostMapping("/list")
    public List<Employee> saveEmployees(@RequestBody List<Employee> employees) {
        return employeeService.saveEmployees(employees);
    }

    // update one employee
    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    // update List of employees
    @PutMapping("/list")
    public List<Employee> updateEmployees(@RequestBody List<Employee> employees) {
        return employeeService.updateEmployees(employees);
    }

    // delete all employees
    @DeleteMapping
    public void deleteAllEmployees() {
        employeeService.deleteAllEmployees();
    }

    // delete employee by ID
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

    // delete employees by List of IDs
    @DeleteMapping("/byIds")
    public void deleteEmployeesByIds(@RequestParam List<Long> ids) {
        employeeService.deleteEmployeesByIds(ids);
    }

    // search by name - function name (derived query)
    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeService.searchByNameFunction(name);
    }

    // search by name - JPQL (non-native query)
    @GetMapping("/search/jpql")
    public List<Employee> searchByNameJPQL(@RequestParam String name) {
        return employeeService.searchByNameJPQL(name);
    }

    // search by name - native query
    @GetMapping("/search/native")
    public List<Employee> searchByNameNative(@RequestParam String name) {
        return employeeService.searchByNameNative(name);
    }
}
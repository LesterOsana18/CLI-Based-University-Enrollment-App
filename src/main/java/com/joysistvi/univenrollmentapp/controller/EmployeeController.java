package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.service.EmployeeService;

// Controller Class
// Handles requests related to employee management
public class EmployeeController {

    // Dependency Injection
    private final EmployeeService employeeService;

    // Constructor
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Retrieve all archived employees
    public List<Employee> getArchivedEmployees() {
        return employeeService.getArchivedEmployees();
    }

    // Retrieve an employee by ID
    public Employee getEmployeeById(int id) {
        return employeeService.getEmployeeById(id);
    }

    // Create a new employee
    public boolean createEmployee(
            Employee employee,
            String password) {

        return employeeService.createEmployee(
                employee,
                password);

    }

    // Update an existing employee
    public boolean updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    // Archive an employee
    public boolean archiveEmployee(int id) {
        return employeeService.archiveEmployee(id);
    }

    // Restore an archived employee
    public boolean restoreEmployee(int id) {
        return employeeService.restoreEmployee(id);
    }

    // Permanently delete an employee
    public boolean deleteEmployee(int id) {
        return employeeService.deleteEmployee(id);
    }

}
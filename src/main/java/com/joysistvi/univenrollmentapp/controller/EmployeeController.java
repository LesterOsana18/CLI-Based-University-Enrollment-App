package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.service.EmployeeService;

// Controller Class
// Handles requests related to employee management
public class EmployeeController {

    // Dependency Injection
    private final EmployeeService employeeService;

    // Constructor
    public EmployeeController(
            EmployeeService employeeService) {

        this.employeeService = employeeService;

    }

    // Retrieve all active employees
    public List<Employee> getActiveEmployees() {
        return employeeService.getActiveEmployees();
    }

    // Retrieve all archived employees
    public List<Employee> getArchivedEmployees() {
        return employeeService.getArchivedEmployees();
    }

    // Create a new employee
    public boolean createEmployee(
            String employeeId,
            String firstName,
            String lastName,
            String username,
            String password,
            Position position) {

        return employeeService.createEmployee(
                employeeId,
                firstName,
                lastName,
                username,
                password,
                position);

    }

    // Update an existing employee
    public boolean updateEmployee(
            Employee employee,
            String employeeId,
            String firstName,
            String lastName,
            Position position,
            Status status) {

        return employeeService.updateEmployee(
                employee.getId(),
                employeeId,
                firstName,
                lastName,
                position,
                employee.getUserId(),
                employee.getUsername(),
                status);

    }

    // Archive an employee
    public boolean archiveEmployee(int id) {
        return employeeService.archiveEmployee(id);
    }

    // Restore an archived employee
    public boolean restoreEmployee(int id) {
        return employeeService.restoreEmployee(id);
    }

}
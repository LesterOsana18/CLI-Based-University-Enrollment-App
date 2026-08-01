package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;

// Service Interface
// Defines the business operations for Employee management
public interface EmployeeService {

    // Retrieve all active employees
    List<Employee> getActiveEmployees();

    // Retrieve archived employees
    List<Employee> getArchivedEmployees();

    // Create an employee
    boolean createEmployee(
            String employeeId,
            String firstName,
            String lastName,
            String username,
            String password,
            Position position);

    // Update an employee
    boolean updateEmployee(
            int id,
            String employeeId,
            String firstName,
            String lastName,
            Position position,
            int userId,
            String username,
            Status status);

    // Archive an employee
    boolean archiveEmployee(int id);

    // Restore an employee
    boolean restoreEmployee(int id);

}
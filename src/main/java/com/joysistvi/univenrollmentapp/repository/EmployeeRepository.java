package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Employee;

// Repository Interface
// Defines the database operations for Employee objects
public interface EmployeeRepository {

    // Retrieve all employees
    List<Employee> getAllEmployees();

    // Retrieve all archived employees
    List<Employee> getArchivedEmployees();

    // Retrieve an employee by ID
    Employee getEmployeeById(int id);

    // Create a new employee
    boolean createEmployee(Employee employee, String hashedPassword);

    // Update an existing employee
    boolean updateEmployee(Employee employee);

    // Archive an employee
    boolean archiveEmployee(int id);

    // Restore an archived employee
    boolean restoreEmployee(int id);

    // Permanently delete an employee
    boolean deleteEmployee(int id);

    // Reset the password of an employee
    boolean resetPassword(int userId, String hashedPassword);

}
package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.repository.EmployeeRepository;
import com.joysistvi.univenrollmentapp.utils.PasswordUtils;

// Service Implementation
// Implements the business logic for employee management
public class EmployeeServiceImpl implements EmployeeService {

    // Dependency Injection
    private final EmployeeRepository employeeRepository;

    // Constructor
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Retrieve all employees
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    // Retrieve all archived employees
    @Override
    public List<Employee> getArchivedEmployees() {
        return employeeRepository.getArchivedEmployees();
    }

    // Retrieve an employee by ID
    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    // Create a new employee
    @Override
    public boolean createEmployee(Employee employee, String password) {

        String hashedPassword = PasswordUtils.hashPassword(password);

        return employeeRepository.createEmployee(
                employee,
                hashedPassword);

    }

    // Update an existing employee
    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    // Archive an employee
    @Override
    public boolean archiveEmployee(int id) {
        return employeeRepository.archiveEmployee(id);
    }

    // Restore an archived employee
    @Override
    public boolean restoreEmployee(int id) {
        return employeeRepository.restoreEmployee(id);
    }

    // Permanently delete an employee
    @Override
    public boolean deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }

    // Reset the password of an employee
    @Override
    public boolean resetPassword(int userId, String password) {

        String hashedPassword =
                PasswordUtils.hashPassword(password);

        return employeeRepository.resetPassword(
                userId,
                hashedPassword);

    }

}
package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.repository.EmployeeRepository;
import com.joysistvi.univenrollmentapp.utils.PasswordUtils;

// Service Implementation
// Implements the business logic for Employee management
public class EmployeeServiceImpl implements EmployeeService {

    // Dependency Injection
    private final EmployeeRepository repository;

    // Constructor
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getActiveEmployees() {
        return repository.getActiveEmployees();
    }

    @Override
    public List<Employee> getArchivedEmployees() {
        return repository.getArchivedEmployees();
    }

    @Override
    public boolean createEmployee(
            String employeeId,
            String firstName,
            String lastName,
            String username,
            String password,
            Position position) {

        Employee employee = new Employee(
                0,
                employeeId,
                firstName,
                lastName,
                position,
                0,
                username,
                Status.ACTIVE);

        return repository.createEmployee(
                employee,
                PasswordUtils.hashPassword(password));
    }

    @Override
    public boolean updateEmployee(
            int id,
            String employeeId,
            String firstName,
            String lastName,
            Position position,
            int userId,
            String username,
            Status status) {

        Employee employee = new Employee(
                id,
                employeeId,
                firstName,
                lastName,
                position,
                userId,
                username,
                status);

        return repository.updateEmployee(employee);

    }

    @Override
    public boolean archiveEmployee(int id) {
        return repository.archiveEmployee(id);
    }

    @Override
    public boolean restoreEmployee(int id) {
        return repository.restoreEmployee(id);
    }

}
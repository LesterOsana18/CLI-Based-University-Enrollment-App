package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.repository.EmployeeRepositoryImpl;
import com.joysistvi.univenrollmentapp.utils.PasswordUtils;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl();

    @Override
    public List<Employee> getActiveEmployees() {
        return repository.getEmployeesByStatus(true);
    }

    @Override
    public List<Employee> getInactiveEmployees() {
        return repository.getEmployeesByStatus(false);
    }

    @Override
    public boolean createEmployee(String employeeId, String firstName, String lastName,
            String username, String password, Position position) {
        Employee employee = new Employee(0, employeeId, firstName, lastName, position, 0, username, Status.ACTIVE);
        return repository.createEmployee(employee, PasswordUtils.hashPassword(password));
    }

    @Override
    public boolean updateEmployee(int id, String employeeId, String firstName, String lastName,
            Position position, int userId, String username, Status status) {
        return repository.updateEmployee(new Employee(id, employeeId, firstName, lastName,
                position, userId, username, status));
    }

    @Override
    public boolean deactivateEmployee(int id) {
        return repository.updateStatus(id, false);
    }

    @Override
    public boolean reactivateEmployee(int id) {
        return repository.updateStatus(id, true);
    }
}

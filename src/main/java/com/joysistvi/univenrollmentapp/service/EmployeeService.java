package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getActiveEmployees();
    List<Employee> getInactiveEmployees();
    boolean createEmployee(String employeeId, String firstName, String lastName,
            String username, String password, Position position);
    boolean updateEmployee(int id, String employeeId, String firstName, String lastName,
            Position position, int userId, String username, Status status);
    boolean deactivateEmployee(int id);
    boolean reactivateEmployee(int id);
}

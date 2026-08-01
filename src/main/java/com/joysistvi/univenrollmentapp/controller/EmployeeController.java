package com.joysistvi.univenrollmentapp.controller;

import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.service.EmployeeServiceImpl;
import java.util.List;

public class EmployeeController {
    private final EmployeeServiceImpl service = new EmployeeServiceImpl();

    public List<Employee> getActiveEmployees() { return service.getActiveEmployees(); }
    public List<Employee> getInactiveEmployees() { return service.getInactiveEmployees(); }

    public boolean createEmployee(String employeeId, String firstName, String lastName,
            String username, String password, Position position) {
        return service.createEmployee(employeeId, firstName, lastName, username, password, position);
    }

    public boolean updateEmployee(Employee employee, String employeeId, String firstName,
            String lastName, Position position, Status status) {
        return service.updateEmployee(employee.getId(), employeeId, firstName, lastName,
                position, employee.getUserId(), employee.getUsername(), status);
    }

    public boolean deactivateEmployee(int id) { return service.deactivateEmployee(id); }
    public boolean reactivateEmployee(int id) { return service.reactivateEmployee(id); }

}

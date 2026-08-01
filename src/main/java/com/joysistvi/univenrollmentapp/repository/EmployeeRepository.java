package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.model.Employee;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> getEmployeesByStatus(boolean active);
    boolean createEmployee(Employee employee, String hashedPassword);
    boolean updateEmployee(Employee employee);
    boolean updateStatus(int id, boolean active);
}

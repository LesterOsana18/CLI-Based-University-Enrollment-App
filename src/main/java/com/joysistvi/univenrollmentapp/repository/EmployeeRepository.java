package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Employee;

public interface EmployeeRepository {

    List<Employee> getActiveEmployees();

    List<Employee> getArchivedEmployees();

    Employee findById(int id);

    boolean save(Employee employee, String hashedPassword);

    boolean update(Employee employee);

    boolean archive(int id);

    boolean restore(int id);

    boolean delete(int id);

}
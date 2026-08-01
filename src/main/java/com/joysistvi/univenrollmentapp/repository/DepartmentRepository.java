package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Department;

// Repository Interface
// Defines the database operations for Department objects
public interface DepartmentRepository {

    // Retrieve all active departments
    List<Department> getAllDepartments();

    // Retrieve all archived departments
    List<Department> getArchivedDepartments();

    // Retrieve a department by ID
    Department findById(int id);

    // Insert a department
    boolean save(Department department);

    // Update a department
    boolean update(int id, String departmentName);

    // Archive a department
    boolean archive(int id);

    // Restore an archived department
    boolean restore(int id);

    // Permanently delete a department
    boolean delete(int id);

}
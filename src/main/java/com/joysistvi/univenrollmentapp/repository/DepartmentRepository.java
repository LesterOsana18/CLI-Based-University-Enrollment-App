package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Department;

// Repository Interface
// Defines the database operations for Department objects
public interface DepartmentRepository {

    // Retrieve all departments
    List<Department> getAllDepartments();

    // Retrieve all archived departments
    List<Department> getArchivedDepartments();

    // Retrieve a department by ID
    Department getDepartmentById(int id);

    // Create a new department
    boolean createDepartment(Department department);

    // Update an existing department
    boolean updateDepartment(Department department);

    // Archive a department
    boolean archiveDepartment(int id);

    // Restore an archived department
    boolean restoreDepartment(int id);

    // Permanently delete a department
    boolean deleteDepartment(int id);
}
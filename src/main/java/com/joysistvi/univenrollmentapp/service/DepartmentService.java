package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Department;

// Service Interface
// Defines the business operations for Department management
public interface DepartmentService {

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
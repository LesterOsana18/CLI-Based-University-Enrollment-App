package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Department;

// Service Interface
// Defines the business operations for Department management
public interface DepartmentService {

    // Retrieve all active departments
    List<Department> getAllDepartments();

    // Retrieve archived departments
    List<Department> getArchivedDepartments();

    // Create a department
    boolean createDepartment(Department department);

    // Update a department
    boolean updateDepartment(int id, String departmentName);

    // Archive a department
    boolean archiveDepartment(int id);

    // Restore an archived department
    boolean restoreDepartment(int id);

}
package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.service.DepartmentService;

// Controller Class
// Handles requests related to department management
public class DepartmentController {

    // Dependency Injection
    private final DepartmentService departmentService;

    // Constructor
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Retrieve all departments
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Retrieve all archived departments
    public List<Department> getArchivedDepartments() {
        return departmentService.getArchivedDepartments();
    }

    // Retrieve a department by ID
    public Department getDepartmentById(int id) {
        return departmentService.getDepartmentById(id);
    }

    // Create a new department
    public boolean createDepartment(Department department) {
        return departmentService.createDepartment(department);
    }

    // Update an existing department
    public boolean updateDepartment(Department department) {
        return departmentService.updateDepartment(department);
    }

    // Archive a department
    public boolean archiveDepartment(int id) {
        return departmentService.archiveDepartment(id);
    }

    // Restore an archived department
    public boolean restoreDepartment(int id) {
        return departmentService.restoreDepartment(id);
    }

    // Permanently delete a department
    public boolean deleteDepartment(int id) {
        return departmentService.deleteDepartment(id);
    }
}
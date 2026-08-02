package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.repository.DepartmentRepository;

// Service Implementation
// Implements the business logic for Department management
public class DepartmentServiceImpl implements DepartmentService {

    // Dependency Injection
    private final DepartmentRepository departmentRepository;

    // Constructor
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.departmentRepository = repository;
    }

    // Retrieve all departments
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    // Retrieve all archived departments
    @Override
    public List<Department> getArchivedDepartments() {
        return departmentRepository.getArchivedDepartments();
    }

    // Retrieve a department by ID
    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.getDepartmentById(id);
    }

    // Create a new department
    @Override
    public boolean createDepartment(Department department) {
        return departmentRepository.createDepartment(department);
    }

    // Update an existing department
    @Override
    public boolean updateDepartment(Department department) {
        return departmentRepository.updateDepartment(department);
    }

    // Archive a department
    @Override
    public boolean archiveDepartment(int id) {
        return departmentRepository.archiveDepartment(id);
    }

    // Restore an archived department
    @Override
    public boolean restoreDepartment(int id) {
        return departmentRepository.restoreDepartment(id);
    }

    // Permanently delete a department
    @Override
    public boolean deleteDepartment(int id) {
        return departmentRepository.deleteDepartment(id);
    }
}
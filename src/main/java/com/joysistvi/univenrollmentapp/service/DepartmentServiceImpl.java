package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.repository.DepartmentRepository;

// Service Implementation
// Implements the business logic for Department management
public class DepartmentServiceImpl implements DepartmentService {

    // Dependency Injection
    private final DepartmentRepository repository;

    // Constructor
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return repository.getAllDepartments();
    }

    @Override
    public List<Department> getArchivedDepartments() {
        return repository.getArchivedDepartments();
    }

    @Override
    public boolean createDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public boolean updateDepartment(int id, String departmentName) {
        return repository.update(id, departmentName);
    }

    @Override
    public boolean archiveDepartment(int id) {
        return repository.archive(id);
    }

    @Override
    public boolean restoreDepartment(int id) {
        return repository.restore(id);
    }

    // Delete a department permanently
    @Override
    public boolean deleteDepartment(int id) {
        return repository.delete(id);
    }
}
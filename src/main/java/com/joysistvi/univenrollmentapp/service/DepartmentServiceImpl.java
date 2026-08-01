package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.repository.DepartmentRepositoryImpl;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepositoryImpl repo = new DepartmentRepositoryImpl();
    @Override
    public List<Department> getAllDepartments() {
        return repo.getAllDepartments();
    }

    @Override
    public List<Department> getArchivedDepartments() {
        return repo.getArchivedDepartments();
    }

    @Override
    public boolean createDepartment(Department department) {
        return repo.createDepartment(department);
    }

    @Override
    public boolean updateDeparment(int id, String department_name) {
        return repo.updateDeparment(id, department_name);
    }

    @Override
    public boolean softDeleteDepartment(int id) {
        return repo.softDeleteDepartment(id);
    }

    @Override
    public boolean hardDeleteDepartment(int id) {
        return repo.hardDeleteDepartment(id);
    }
   
}

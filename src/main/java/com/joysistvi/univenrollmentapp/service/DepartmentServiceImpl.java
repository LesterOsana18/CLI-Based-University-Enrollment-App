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
    public boolean createDepartment(Department department) {
        return repo.createDepartment(department);
    }

    @Override
    public boolean updateDeparment(int id, String department_name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean softDeleteDepartment(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean hardDeleteDepartment(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}

package com.joysistvi.univenrollmentapp.controller;


import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.service.DepartmentServiceImpl;
import java.util.List;

public class DepartmentController  {
    DepartmentServiceImpl service = new DepartmentServiceImpl();
    
    public List<Department> getAllDepartments(){
        return service.getAllDepartments();
    }

    public List<Department> getArchivedDepartments() {
        return service.getArchivedDepartments();
    }

    public boolean createDepartment(String departmentName) {
        return service.createDepartment(new Department(0, departmentName));
    }

    public boolean updateDepartment(int id, String department_name) {
        return service.updateDeparment(id, department_name);
    }

    public boolean deleteDepartment(int id) {
        return service.softDeleteDepartment(id);
    }

    public boolean permanentlyDeleteDepartment(int id) {
        return service.hardDeleteDepartment(id);
    }
}

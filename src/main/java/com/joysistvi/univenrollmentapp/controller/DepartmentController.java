package com.joysistvi.univenrollmentapp.controller;


import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.service.DepartmentServiceImpl;
import java.util.List;

public class DepartmentController  {
    DepartmentServiceImpl service = new DepartmentServiceImpl();
    
    public List<Department> getAllDepartments(){
        return service.getAllDepartments();
    }
}

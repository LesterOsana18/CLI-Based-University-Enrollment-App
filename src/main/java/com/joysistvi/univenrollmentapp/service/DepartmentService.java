package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    List<Department> getArchivedDepartments();
    boolean createDepartment(Department department);
    boolean updateDeparment(int id, String departmentName);
    boolean softDeleteDepartment(int id);
    boolean hardDeleteDepartment(int id);
}

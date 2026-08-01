package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.model.Department;
import java.util.List;

public interface DepartmentRepository {
    public List<Department> getAllDepartments();
    public boolean createDepartment(Department department);
    public boolean updateDeparment(int id, String department_name);
    public boolean softDeleteDepartment(int id);
    public boolean hardDeleteDepartment(int id);
}

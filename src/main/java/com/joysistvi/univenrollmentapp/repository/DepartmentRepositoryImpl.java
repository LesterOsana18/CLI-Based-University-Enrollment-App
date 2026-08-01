package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.model.Department;
import java.sql.*;
import java.util.*;


public class DepartmentRepositoryImpl implements DepartmentRepository{
    
    @Override
    public List<Department> getAllDepartments() {
       List<Department>departments = new ArrayList<>();
       String query = "SELECT * FROM departments WHERE is_archived = 0";
       
       try(Connection conn = new DbConnection().getConnection();
           Statement stmt = conn.createStatement()){
           ResultSet rs = stmt.executeQuery(query);
           while(rs.next()){
               departments.add(new Department(rs.getInt("id"), rs.getString("department_name")));
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       
       return departments;
    }

    @Override
    public boolean createDepartment(Department department) {
        String query = "INSERT INTO departments (department_name) VALUES(?)";
        try(Connection conn = new DbConnection().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, department.getDepartmentName());
            int rowAffected = pstmt.executeUpdate();
            return rowAffected > 0;
        }catch(SQLException e){
           System.out.println(e.getMessage());
           return false;
       }
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

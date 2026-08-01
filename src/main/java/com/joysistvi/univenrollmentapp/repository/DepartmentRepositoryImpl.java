package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.model.Department;
import java.sql.*;
import java.util.*;


public class DepartmentRepositoryImpl implements DepartmentRepository{
    
    @Override
    public List<Department> getAllDepartments() {
       return getDepartmentsByArchiveStatus(false);
    }

    @Override
    public List<Department> getArchivedDepartments() {
       return getDepartmentsByArchiveStatus(true);
    }

    private List<Department> getDepartmentsByArchiveStatus(boolean archived) {
       List<Department> departments = new ArrayList<>();
       String query = "SELECT id, department_name FROM departments WHERE is_archived = ? ORDER BY department_name";
       
       try(Connection conn = new DbConnection().getConnection();
           PreparedStatement pstmt = conn.prepareStatement(query)){
           pstmt.setBoolean(1, archived);
           ResultSet rs = pstmt.executeQuery();
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
        String query = "UPDATE departments SET department_name = ? WHERE id = ? AND is_archived = 0";
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, department_name);
            pstmt.setInt(2, id);
            int rowAffected = pstmt.executeUpdate();
            return rowAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean softDeleteDepartment(int id) {
        String query = "UPDATE departments SET is_archived = 1 WHERE id = ? AND is_archived = 0";
        return executeUpdate(query, id);
    }

    @Override
    public boolean hardDeleteDepartment(int id) {
        String query = "DELETE FROM departments WHERE id = ? AND is_archived = 1";
        return executeUpdate(query, id);
    }

    private boolean executeUpdate(String query, int id) {
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}

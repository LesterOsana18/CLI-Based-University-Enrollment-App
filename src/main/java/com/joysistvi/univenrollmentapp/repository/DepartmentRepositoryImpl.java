package com.joysistvi.univenrollmentapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.model.Department;

// Repository Implementation
// Implements database operations for Department objects
public class DepartmentRepositoryImpl implements DepartmentRepository {

    // Database Connection
    private final DbConnection dbConnection;

    // Constructor for Dependency Injection
    public DepartmentRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Retrieve all active departments
    @Override
    public List<Department> getAllDepartments() {
        return findDepartments(false);
    }

    // Retrieve all archived departments
    @Override
    public List<Department> getArchivedDepartments() {
        return findDepartments(true);
    }

    // Retrieve a department by ID
    @Override
    public Department getDepartmentById(int id) {

        String sql = """
                SELECT *
                FROM departments
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("department_name"));

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return null;

    }

    // Helper method to retrieve departments based on their archived status
    private List<Department> findDepartments(boolean archived) {

        List<Department> departments = new ArrayList<>();

        String sql = """
                SELECT *
                FROM departments
                WHERE is_archived = ?
                ORDER BY department_name
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBoolean(1, archived);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                departments.add(new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("department_name")));

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return departments;

    }

    // Create a new department
    @Override
    public boolean createDepartment(Department department) {

        String sql =
                "INSERT INTO departments (department_name) VALUES (?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, department.getDepartmentName());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Update an existing department
    @Override
    public boolean updateDepartment(Department department) {

        String sql = """
                UPDATE departments
                SET department_name = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {


            statement.setString(1, department.getDepartmentName());
            statement.setInt(2, department.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Archive a department
    @Override
    public boolean archiveDepartment(int id) {
        return executeUpdate(
                "UPDATE departments SET is_archived = TRUE WHERE id = ?",
                id);
    }

    // Restore an archived department
    @Override
    public boolean restoreDepartment(int id) {
        return executeUpdate(
                "UPDATE departments SET is_archived = FALSE WHERE id = ?",
                id);
    }

    // Permanently delete a department
    @Override
    public boolean deleteDepartment(int id) {
        return executeUpdate(
                "DELETE FROM departments WHERE id = ?",
                id);
    }

    // Helper method for UPDATE/DELETE queries that only require an ID
    private boolean executeUpdate(String sql, int id) {

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

}
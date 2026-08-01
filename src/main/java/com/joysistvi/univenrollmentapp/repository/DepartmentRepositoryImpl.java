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

    private final DbConnection dbConnection;

    public DepartmentRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Department> getAllDepartments() {
        return findDepartments(false);
    }

    @Override
    public List<Department> getArchivedDepartments() {
        return findDepartments(true);
    }

    @Override
    public Department findById(int id) {

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

    @Override
    public boolean save(Department department) {

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

    @Override
    public boolean update(int id, String departmentName) {

        String sql = """
                UPDATE departments
                SET department_name = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, departmentName);
            statement.setInt(2, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    @Override
    public boolean archive(int id) {
        return executeUpdate(
                "UPDATE departments SET is_archived = TRUE WHERE id = ?",
                id);
    }

    @Override
    public boolean restore(int id) {
        return executeUpdate(
                "UPDATE departments SET is_archived = FALSE WHERE id = ?",
                id);
    }

    @Override
    public boolean delete(int id) {
        return executeUpdate(
                "DELETE FROM departments WHERE id = ?",
                id);
    }

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
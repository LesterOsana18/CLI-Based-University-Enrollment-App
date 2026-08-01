package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public List<Employee> getEmployeesByStatus(boolean active) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT e.id, e.employee_id, e.first_name, e.last_name, e.position, "
                + "e.user_id, e.status, u.username FROM employees e "
                + "JOIN users u ON e.user_id = u.id WHERE e.status = ? ORDER BY e.employee_id";
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, active ? Status.ACTIVE.name() : Status.INACTIVE.name());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employees.add(mapEmployee(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public boolean createEmployee(Employee employee, String hashedPassword) {
        String createUser = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        String createEmployee = "INSERT INTO employees (employee_id, first_name, last_name, position, user_id, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DbConnection().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement userStatement = conn.prepareStatement(createUser, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement employeeStatement = conn.prepareStatement(createEmployee)) {
                userStatement.setString(1, employee.getUsername());
                userStatement.setString(2, hashedPassword);
                userStatement.setString(3, employee.getPosition().name());
                userStatement.executeUpdate();

                int userId;
                try (ResultSet keys = userStatement.getGeneratedKeys()) {
                    if (!keys.next()) throw new SQLException("Unable to create the employee user account.");
                    userId = keys.getInt(1);
                }

                employeeStatement.setString(1, employee.getEmployeeId());
                employeeStatement.setString(2, employee.getFirstName());
                employeeStatement.setString(3, employee.getLastName());
                employeeStatement.setString(4, employee.getPosition().name());
                employeeStatement.setInt(5, userId);
                employeeStatement.setString(6, employee.getStatus().name());
                employeeStatement.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        String updateEmployee = "UPDATE employees SET employee_id = ?, first_name = ?, last_name = ?, position = ?, status = ? "
                + "WHERE id = ?";
        String updateUserRole = "UPDATE users SET role = ? WHERE id = ?";
        try (Connection conn = new DbConnection().getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement employeeStatement = conn.prepareStatement(updateEmployee);
                 PreparedStatement userStatement = conn.prepareStatement(updateUserRole)) {
                employeeStatement.setString(1, employee.getEmployeeId());
                employeeStatement.setString(2, employee.getFirstName());
                employeeStatement.setString(3, employee.getLastName());
                employeeStatement.setString(4, employee.getPosition().name());
                employeeStatement.setString(5, employee.getStatus().name());
                employeeStatement.setInt(6, employee.getId());
                if (employeeStatement.executeUpdate() == 0) {
                    conn.rollback();
                    return false;
                }
                userStatement.setString(1, employee.getPosition().name());
                userStatement.setInt(2, employee.getUserId());
                userStatement.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateStatus(int id, boolean active) {
        String query = "UPDATE employees SET status = ? WHERE id = ?";
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, active ? Status.ACTIVE.name() : Status.INACTIVE.name());
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Employee mapEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"), rs.getString("employee_id"),
                rs.getString("first_name"), rs.getString("last_name"),
                Position.valueOf(rs.getString("position")), rs.getInt("user_id"),
                rs.getString("username"), Status.valueOf(rs.getString("status")));
    }
}

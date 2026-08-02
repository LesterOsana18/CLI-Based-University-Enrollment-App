package com.joysistvi.univenrollmentapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;

// Repository Implementation
// Implements database operations for Employee objects
public class EmployeeRepositoryImpl implements EmployeeRepository {

    // Database Connection
    private final DbConnection dbConnection;

    // Constructor for Dependency Injection
    public EmployeeRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Retrieve all employees
    @Override
    public List<Employee> getAllEmployees() {
        return findEmployees(false);
    }

    // Retrieve all archived employees
    @Override
    public List<Employee> getArchivedEmployees() {
        return findEmployees(true);
    }

    // Retrieve an employee by ID
    @Override
    public Employee getEmployeeById(int id) {

        String sql = """
                SELECT e.id,
                       e.employee_id,
                       e.first_name,
                       e.last_name,
                       e.position,
                       e.user_id,
                       e.status,
                       u.username
                FROM employees e
                JOIN users u
                    ON e.user_id = u.id
                WHERE e.id = ?
                  AND e.is_archived = FALSE
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapEmployee(resultSet);
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return null;

    }

    // Helper method to retrieve employees
    private List<Employee> findEmployees(boolean archived) {

        List<Employee> employees = new ArrayList<>();

        String sql = """
                SELECT e.id,
                       e.employee_id,
                       e.first_name,
                       e.last_name,
                       e.position,
                       e.user_id,
                       e.status,
                       u.username
                FROM employees e
                JOIN users u
                    ON e.user_id = u.id
                WHERE e.is_archived = ?
                ORDER BY e.employee_id
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBoolean(1, archived);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                employees.add(mapEmployee(resultSet));

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return employees;

    }

    // Create a new employee
    @Override
    public boolean createEmployee(Employee employee, String hashedPassword) {

        String createUser = """
                INSERT INTO users (username, password, role)
                VALUES (?, ?, ?)
                """;

        String createEmployee = """
                INSERT INTO employees (
                    employee_id,
                    first_name,
                    last_name,
                    position,
                    user_id,
                    status
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = dbConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement userStatement =
                         connection.prepareStatement(
                                 createUser,
                                 Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement employeeStatement =
                         connection.prepareStatement(createEmployee)) {

                userStatement.setString(1, employee.getUsername());
                userStatement.setString(2, hashedPassword);
                userStatement.setString(3, employee.getPosition().name());

                userStatement.executeUpdate();

                ResultSet generatedKeys =
                        userStatement.getGeneratedKeys();

                if (!generatedKeys.next()) {

                    connection.rollback();
                    return false;

                }

                int userId = generatedKeys.getInt(1);

                employeeStatement.setString(1, employee.getEmployeeId());
                employeeStatement.setString(2, employee.getFirstName());
                employeeStatement.setString(3, employee.getLastName());
                employeeStatement.setString(4, employee.getPosition().name());
                employeeStatement.setInt(5, userId);
                employeeStatement.setString(6, employee.getStatus().name());

                employeeStatement.executeUpdate();

                connection.commit();

                return true;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("Database Error: " + e.getMessage());

            } finally {

                connection.setAutoCommit(true);

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Update an existing employee
    @Override
    public boolean updateEmployee(Employee employee) {

        String updateEmployee = """
                UPDATE employees
                SET employee_id = ?,
                    first_name = ?,
                    last_name = ?,
                    position = ?,
                    status = ?
                WHERE id = ?
                """;

        String updateUser = """
                UPDATE users
                SET role = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement employeeStatement =
                         connection.prepareStatement(updateEmployee);
                 PreparedStatement userStatement =
                         connection.prepareStatement(updateUser)) {

                employeeStatement.setString(1, employee.getEmployeeId());
                employeeStatement.setString(2, employee.getFirstName());
                employeeStatement.setString(3, employee.getLastName());
                employeeStatement.setString(4, employee.getPosition().name());
                employeeStatement.setString(5, employee.getStatus().name());
                employeeStatement.setInt(6, employee.getId());

                employeeStatement.executeUpdate();

                userStatement.setString(1, employee.getPosition().name());
                userStatement.setInt(2, employee.getUserId());

                userStatement.executeUpdate();

                connection.commit();

                return true;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("Database Error: " + e.getMessage());

            } finally {

                connection.setAutoCommit(true);

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Archive an employee
    @Override
    public boolean archiveEmployee(int id) {
        return executeUpdate(
                "UPDATE employees SET is_archived = TRUE WHERE id = ?",
                id);
    }

    // Restore an archived employee
    @Override
    public boolean restoreEmployee(int id) {
        return executeUpdate(
                "UPDATE employees SET is_archived = FALSE WHERE id = ?",
                id);
    }

    // Permanently delete an employee
    @Override
    public boolean deleteEmployee(int id) {
        return executeUpdate(
                "DELETE FROM employees WHERE id = ?",
                id);
    }

    // Helper method to execute update queries
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

    // Reset the password of an employee
    @Override
    public boolean resetPassword(int userId, String hashedPassword) {

        String sql = """
                UPDATE users
                SET password = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)) {

            statement.setString(1, hashedPassword);
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;
    }

    // Helper method to map a ResultSet into an Employee object
    private Employee mapEmployee(ResultSet resultSet) throws SQLException {

        return new Employee(
                resultSet.getInt("id"),
                resultSet.getString("employee_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                Position.valueOf(resultSet.getString("position")),
                resultSet.getInt("user_id"),
                resultSet.getString("username"),
                Status.valueOf(resultSet.getString("status")));

    }

}
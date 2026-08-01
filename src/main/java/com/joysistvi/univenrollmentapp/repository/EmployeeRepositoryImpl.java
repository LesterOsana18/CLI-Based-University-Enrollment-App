package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final DbConnection dbConnection;

    public EmployeeRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Employee> getActiveEmployees() {

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
                WHERE e.is_archived = FALSE
                ORDER BY e.employee_id
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapEmployee(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return employees;

    }

    @Override
    public List<Employee> getArchivedEmployees() {

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
                WHERE e.is_archived = TRUE
                ORDER BY e.employee_id
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                employees.add(mapEmployee(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return employees;

    }

    @Override
    public Employee findById(int id) {

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
             PreparedStatement statement = connection.prepareStatement(sql)) {

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

    @Override
    public boolean save(Employee employee, String hashedPassword) {

        String createUser = """
                INSERT INTO users(username,password,role)
                VALUES(?,?,?)
                """;

        String createEmployee = """
                INSERT INTO employees(
                    employee_id,
                    first_name,
                    last_name,
                    position,
                    user_id,
                    status
                )
                VALUES(?,?,?,?,?,?)
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

                ResultSet keys = userStatement.getGeneratedKeys();

                if (!keys.next()) {
                    connection.rollback();
                    return false;
                }

                int userId = keys.getInt(1);

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

    @Override
    public boolean update(Employee employee) {

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

    @Override
    public boolean archive(int id) {

        String sql = """
                UPDATE employees
                SET is_archived = TRUE
                WHERE id = ?
                  AND is_archived = FALSE
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    @Override
    public boolean restore(int id) {

        String sql = """
                UPDATE employees
                SET is_archived = FALSE
                WHERE id = ?
                  AND is_archived = TRUE
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    @Override
    public boolean delete(int id) {

        String sql = """
                DELETE FROM employees
                WHERE id = ?
                  AND is_archived = TRUE
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    private Employee mapEmployee(ResultSet rs) throws SQLException {

        return new Employee(
                rs.getInt("id"),
                rs.getString("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                Position.valueOf(rs.getString("position")),
                rs.getInt("user_id"),
                rs.getString("username"),
                Status.valueOf(rs.getString("status"))
        );

    }

}
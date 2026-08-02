package com.joysistvi.univenrollmentapp.repository;

// Java SQL and Utility Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.enums.Role;
import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.utils.PasswordUtils;

// Repository Implementation Class
// Implements all database operations for User objects
public class UserRepositoryImpl implements UserRepository {

    // Dependency Injection
    private final DbConnection dbConnection;

    // Constructor
    public UserRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Retrieve all users
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, password, role, created_at FROM users ORDER BY username";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return users;

    }

    // Retrieve all archived users
    @Override
    public List<User> getArchivedUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, password, role, created_at FROM users WHERE is_archived = TRUE ORDER BY username";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return users;

    }

    // Retrieve a user by ID
    @Override
    public User getUserById(int id) {

        String sql = "SELECT id, username, password, role, created_at FROM users WHERE id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? mapUser(resultSet) : null;
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return null;

    }

    // Retrieve a user by username
    @Override
    public User getUserByUsername(String username) {

        String sql =
                "SELECT * FROM users WHERE username = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return mapUser(resultSet);

            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return null;

    }

    // Create a new user
    @Override
    public boolean createUser(User user) {

        String sql = """
            INSERT INTO users (username, password, role)
            VALUES (?, ?, ?)
            """;

        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().name());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Update an existing user
    @Override
    public boolean updateUser(User user) {

        boolean changePassword = user.getPassword() != null && !user.getPassword().isBlank();
        String sql = changePassword
                ? "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?"
                : "UPDATE users SET username = ?, role = ? WHERE id = ?";
        try (Connection connection = dbConnection.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                if (user.getRole() == Role.STUDENT && hasEmployeeProfile(connection, user.getId())) {
                    connection.rollback();
                    return false;
                }

                statement.setString(1, user.getUsername());
                int parameter = 2;
                if (changePassword) {
                    statement.setString(parameter++, user.getPassword());
                }
                statement.setString(parameter++, user.getRole().name());
                statement.setInt(parameter, user.getId());
                if (statement.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }

                if (user.getRole() != Role.STUDENT) {
                    try (PreparedStatement employeeStatement = connection.prepareStatement(
                            "UPDATE employees SET position = ? WHERE user_id = ?")) {
                        employeeStatement.setString(1, user.getRole().name());
                        employeeStatement.setInt(2, user.getId());
                        employeeStatement.executeUpdate();
                    }
                }
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Database Error: " + e.getMessage());
                return false;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return false;

    }

    // Archive a user
    @Override
    public boolean archiveUser(int id) {

        String sql =
                "UPDATE users " +
                "SET is_archived = TRUE " +
                "WHERE id = ? AND is_archived = FALSE";

        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Restore an archived user
    @Override
    public boolean restoreUser(int id) {

        String sql =
                "UPDATE users " +
                "SET is_archived = FALSE " +
                "WHERE id = ? AND is_archived = TRUE";

        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Permanently delete a user
    @Override
    public boolean deleteUser(int id) {

        String sql =
                "DELETE FROM users WHERE id = ?";

        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Check if a username already exists
    @Override
    public boolean usernameExists(String username) {

        return getUserByUsername(username) != null;

    }

    // Register a new student account
    @Override
    public String registerStudentAccount(
            String studentNumber,
            String email,
            String username,
            String password) {

        String findStudent = """
                SELECT id, email, user_id
                FROM students
                WHERE student_number = ?
                """;

        String createUser = """
                INSERT INTO users(
                    username,
                    password,
                    role
                )
                VALUES (?, ?, ?)
                """;

        String linkStudent = """
                UPDATE students
                SET user_id = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {

                // Check username
                if (usernameExists(username)) {

                    connection.rollback();
                    return "USERNAME_EXISTS";

                }

                Integer studentId = null;

                try (PreparedStatement statement =
                        connection.prepareStatement(findStudent)) {

                    statement.setString(1, studentNumber);

                    ResultSet resultSet = statement.executeQuery();

                    if (!resultSet.next()) {

                        connection.rollback();
                        return "STUDENT_NOT_FOUND";

                    }

                    if (!resultSet.getString("email")
                            .equalsIgnoreCase(email)) {

                        connection.rollback();
                        return "EMAIL_MISMATCH";

                    }

                    if (resultSet.getObject("user_id") != null) {

                        connection.rollback();
                        return "ACCOUNT_ALREADY_EXISTS";

                    }

                    studentId = resultSet.getInt("id");

                }

                int userId;

                try (PreparedStatement statement =
                        connection.prepareStatement(
                                createUser,
                                Statement.RETURN_GENERATED_KEYS)) {

                    statement.setString(1, username);
                    statement.setString(
                            2,
                            PasswordUtils.hashPassword(password));
                    statement.setString(3, Role.STUDENT.name());

                    statement.executeUpdate();

                    ResultSet keys = statement.getGeneratedKeys();

                    if (!keys.next()) {

                        connection.rollback();
                        return "ERROR";

                    }

                    userId = keys.getInt(1);

                }

                try (PreparedStatement statement =
                        connection.prepareStatement(linkStudent)) {

                    statement.setInt(1, userId);
                    statement.setInt(2, studentId);

                    if (statement.executeUpdate() == 0) {

                        connection.rollback();
                        return "ERROR";

                    }

                }

                connection.commit();

                return "SUCCESS";

            } catch (SQLException e) {

                connection.rollback();

                System.out.println(
                        "Database Error: "
                                + e.getMessage());

                return "ERROR";

            } finally {

                connection.setAutoCommit(true);

            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: "
                            + e.getMessage());

            return "ERROR";

        }

    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                Role.valueOf(resultSet.getString("role")),
                resultSet.getTimestamp("created_at"));
    }

    private boolean hasEmployeeProfile(Connection connection, int userId) throws SQLException {
        String sql = "SELECT 1 FROM employees WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}

package com.joysistvi.univenrollmentapp.repository;

// Java SQL and Utility Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.enums.Role;
import com.joysistvi.univenrollmentapp.model.User;

// Repository Implementation Class
// Implements all database operations for User objects
public class UserRepositoryImpl implements UserRepository {

    // Dependency Injection
    private final DbConnection dbConnection;

    // Constructor
    public UserRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        // TODO

        return users;

    }

    @Override
    public User findById(int id) {

        // TODO

        return null;

    }

    @Override
    public User findByUsername(String username) {

        String sql =
                "SELECT * FROM users WHERE username = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(
                        Role.valueOf(
                                resultSet.getString("role")));

                user.setCreatedAt(
                        resultSet.getTimestamp("created_at"));

                return user;

            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return null;

    }

    @Override
    public boolean save(User user) {

        // TODO

        return false;

    }

    @Override
    public boolean update(User user) {

        // TODO

        return false;

    }

    @Override
    public boolean delete(int id) {

        // TODO

        return false;

    }

    @Override
    public boolean usernameExists(String username) {

        return findByUsername(username) != null;

    }
}

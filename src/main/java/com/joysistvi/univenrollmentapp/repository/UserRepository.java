package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.User;

// Repository Interface
// Defines the database operations for User objects
public interface UserRepository {

    // Retrieve all users
    List<User> getAllUsers();

    // Retrieve all archived users
    List<User> getArchivedUsers();

    // Retrieve a user by ID
    User getUserById(int id);

    // Retrieve a user by username
    User getUserByUsername(String username);

    // Insert a new user
    boolean createUser(User user);

    // Update an existing user
    boolean updateUser(User user);

    // Archive a user
    boolean archiveUser(int id);

    // Restore an archived user
    boolean restoreUser(int id);

    // Permanently delete a user
    boolean deleteUser(int id);

    // Check if a username already exists
    boolean usernameExists(String username);

    // Register a new student account
    String registerStudentAccount(
        String studentNumber,
        String email,
        String username,
        String password);
}

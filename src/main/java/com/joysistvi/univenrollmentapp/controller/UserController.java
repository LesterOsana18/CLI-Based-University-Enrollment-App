package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.service.UserService;

// Controller Class
// Handles requests related to user management
public class UserController {

    // Dependency Injection
    private final UserService userService;

    // Constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Retrieve all archived users
    public List<User> getArchivedUsers() {
        return userService.getArchivedUsers();
    }

    // Retrieve a user by ID
    public User getUserById(int id) {
        return userService.getUserById(id);
    }

    // Authenticate a user
    public User login(
            String username,
            String password) {

        return userService.login(
                username,
                password);

    }

    // Create a new user
    public boolean createUser(User user) {
        return userService.createUser(user);
    }

    // Update a user
    public boolean updateUser(User user) {
        return userService.updateUser(user);
    }

    // Archive a user
    public boolean archiveUser(int id) {
        return userService.archiveUser(id);
    }

    // Restore a user
    public boolean restoreUser(int id) {
        return userService.restoreUser(id);
    }

    // Permanently delete a user
    public boolean deleteUser(int id) {
        return userService.deleteUser(id);
    }

    // Register a new student account
    public String registerStudentAccount(
            String studentNumber,
            String email,
            String username,
            String password) {

        return userService.registerStudentAccount(
                studentNumber,
                email,
                username,
                password);

    }
}
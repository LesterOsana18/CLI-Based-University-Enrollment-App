package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.service.UserService;

// Controller Class
// Acts as the bridge between the View and the Service layer
public class UserController {

    // Dependency Injection (Constructor Injection)
    private final UserService userService;

    // Constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Retrieve all users
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    // Retrieve a user by ID
    public User getUserById(int id) {
        return userService.getUserById(id);
    }

    // Authenticate a user
    public User login(String username, String password) {
        return userService.login(username, password);
    }

    // Register a new user
    public boolean register(User user) {
        return userService.register(user);
    }

    // Update a user
    public boolean updateUser(User user) {
        return userService.updateUser(user);
    }

    // Delete a user
    public boolean deleteUser(int id) {
        return userService.deleteUser(id);
    }
}

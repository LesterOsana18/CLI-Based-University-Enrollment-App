package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.repository.UserRepository;
import com.joysistvi.univenrollmentapp.utils.PasswordUtils;

// Service Implementation
// Implements the business operations for User objects
public class UserServiceImpl implements UserService {

    // Dependency Injection
    private final UserRepository userRepository;

    // Constructor
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve all users
    @Override
    public List<User> getAllUsers() {

        // TODO
        return null;

    }

    // Retrieve a user by ID
    @Override
    public User getUserById(int id) {

        // TODO
        return null;

    }

    // Authenticate a user
    @Override
    public User login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        if (!PasswordUtils.verifyPassword(password, user.getPassword())) {
            return null;
        }

        return user;

    }

    // Register a new user
    @Override
    public boolean register(User user) {

        if (userRepository.usernameExists(user.getUsername())) {
            return false;
        }

        user.setPassword(
                PasswordUtils.hashPassword(user.getPassword()));

        return userRepository.save(user);

    }

    // Update an existing user
    @Override
    public boolean updateUser(User user) {

        // TODO
        return false;

    }

    // Delete a user
    @Override
    public boolean deleteUser(int id) {

        // TODO
        return false;

    }
}

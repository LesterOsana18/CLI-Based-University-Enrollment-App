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

        return userRepository.getAllUsers();

    }

    // Retrieve all archived users
    @Override
    public List<User> getArchivedUsers() {
        return userRepository.getArchivedUsers();
    }

    // Retrieve a user by ID
    @Override
    public User getUserById(int id) {

        return userRepository.getUserById(id);

    }

    // Authenticate a user
    @Override
    public User login(String username, String password) {

        User user = userRepository.getUserByUsername(username);

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
    public boolean createUser(User user) {

        if (userRepository.usernameExists(user.getUsername())) {
            return false;
        }

        user.setPassword(
                PasswordUtils.hashPassword(user.getPassword()));

        return userRepository.createUser(user);

    }

    // Update an existing user
    @Override
    public boolean updateUser(User user) {

        User existingUser = userRepository.getUserById(user.getId());
        if (existingUser == null) {
            return false;
        }

        User userWithSameUsername = userRepository.getUserByUsername(user.getUsername());
        if (userWithSameUsername != null
                && userWithSameUsername.getId() != user.getId()) {

            return false;

        }

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
        } else {
            user.setPassword(null);
        }
        return userRepository.updateUser(user);

    }

    // Archive a user
    @Override
    public boolean archiveUser(int id) {

        return userRepository.archiveUser(id);

    }

    // Restore an archived user
    @Override
    public boolean restoreUser(int id) {

        return userRepository.restoreUser(id);

    }

    // Permanently delete a user
    @Override
    public boolean deleteUser(int id) {

        return userRepository.deleteUser(id);

    }

    // Register a new student account
    @Override
    public String registerStudentAccount(String studentNumber, String email, String username, String password) {

        return userRepository.registerStudentAccount(
            studentNumber,
            email,
            username,
            password);
    }
}

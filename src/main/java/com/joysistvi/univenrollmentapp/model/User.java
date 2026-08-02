package com.joysistvi.univenrollmentapp.model;

import java.sql.Timestamp;

import com.joysistvi.univenrollmentapp.enums.Role;

// Model Class
// Stores the information of a User object using encapsulation
public class User {

    // Private fields (Encapsulation)
    private int id;
    private String username;
    private String password;
    private Role role;
    private Timestamp createdAt;

    // Default constructor
    public User() {
    }

    // Constructor for existing records (includes ID)
    public User(int id, String username, String password,
            Role role, Timestamp createdAt) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;

    }

    // Constructor for creating a new user
    public User(String username, String password, Role role) {

        this.username = username;
        this.password = password;
        this.role = role;

    }

    // Constructor for displaying user information (excludes password)
    public User(int id, String username, Role role, Timestamp createdAt) {

        this.id = id;
        this.username = username;
        this.role = role;
        this.createdAt = createdAt;

    }

    // ==========================================================
    // Getter (Accessor) and Setter (Mutator) Methods
    // ==========================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

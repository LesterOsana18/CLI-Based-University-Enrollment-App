package com.joysistvi.univenrollmentapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC Standard Practice
public class DbConnection {

    // Database connection parameters
    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    // Create and return a database connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

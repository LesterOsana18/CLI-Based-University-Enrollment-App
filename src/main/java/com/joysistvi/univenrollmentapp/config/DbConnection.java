package com.joysistvi.univenrollmentapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/university_enrollment_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root@123";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
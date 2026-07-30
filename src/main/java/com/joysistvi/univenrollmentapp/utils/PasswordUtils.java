package com.joysistvi.univenrollmentapp.utils;

import org.mindrot.jbcrypt.BCrypt;

// Utility Class
// Provides reusable methods for password hashing and verification

// All password operations in the application should go through this class.
public final class PasswordUtils {

    // Prevent instantiation
    private PasswordUtils() {
    }

    // Hash a password using BCrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Verify a password against its hash
    public static boolean verifyPassword(String rawPassword,
                                        String hashedPassword) {

        return BCrypt.checkpw(rawPassword, hashedPassword);

    }
}

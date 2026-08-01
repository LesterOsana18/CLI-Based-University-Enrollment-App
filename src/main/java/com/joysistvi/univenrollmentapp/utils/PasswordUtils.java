package com.joysistvi.univenrollmentapp.utils;

import org.mindrot.jbcrypt.BCrypt;

// Utility Class
// Provides reusable methods for password hashing, verification,
// and password validation.
public final class PasswordUtils {

    // BCrypt cost factor
    private static final int LOG_ROUNDS = 12;

    // Prevent instantiation
    private PasswordUtils() {
    }

    // Hash a password using BCrypt
    public static String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt(LOG_ROUNDS));

    }

    // Verify a password against its hash
    public static boolean verifyPassword(
            String rawPassword,
            String hashedPassword) {

        if (rawPassword == null || hashedPassword == null) {
            return false;
        }

        return BCrypt.checkpw(rawPassword, hashedPassword);

    }

    // Validate password requirements
    public static boolean isValidPassword(String password) {

        if (password == null) {
            return false;
        }

        // At least 8 characters
        if (password.length() < 8) {
            return false;
        }

        // At least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // At least one special character
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            return false;
        }

        return true;

    }

    // Returns the reason why a password is invalid.
    // Returns null if the password is valid.
    public static String getPasswordValidationMessage(String password) {

        if (password == null || password.isBlank()) {
            return "Password cannot be empty.";
        }

        if (password.length() < 8) {
            return "Password must be at least 8 characters long.";
        }

        if (!password.matches(".*\\d.*")) {
            return "Password must contain at least one digit (0-9).";
        }

        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            return "Password must contain at least one special character.";
        }

        return null;

    }
}

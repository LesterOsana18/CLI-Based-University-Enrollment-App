package com.joysistvi.univenrollmentapp.utils;

// Utility Class
// Provides standardized console messages throughout the application
public final class MessagePrinter {

    // Prevent instantiation
    private MessagePrinter() {
    }

    // Prints a success message
    public static void success(String message) {

        System.out.println("[SUCCESS] " + message);

    }

    // Prints an error message
    public static void error(String message) {

        System.out.println("[ERROR] " + message);

    }

    // Prints a warning message
    public static void warning(String message) {

        System.out.println("[WARNING] " + message);

    }

    // Prints an informational message
    public static void info(String message) {

        System.out.println("[INFO] " + message);

    }

    // Feature placeholder message
    public static void todo(String featureName) {

        System.out.println("[TODO] " + featureName
                + " feature is not implemented yet.");

    }

    // Prints a generic separator before important messages
    public static void section() {

        BorderPrinter.line();

    }
}

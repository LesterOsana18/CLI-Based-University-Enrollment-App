package com.joysistvi.univenrollmentapp.utils;

// Utility Class
// Provides standardized console prompts throughout the application
public final class PromptUtils {

    // Prevent instantiation
    private PromptUtils() {
    }

    // Prints a generic input prompt
    public static void prompt(String fieldName) {

        System.out.print(fieldName + ": ");

    }

    // Prints the menu selection prompt
    public static void menuChoice() {

        System.out.print("Enter your choice: ");

    }

    // Username prompt
    public static void username() {

        prompt("Username");

    }

    // Password prompt
    public static void password() {

        prompt("Password");

    }

    // Confirm password prompt
    public static void confirmPassword() {

        prompt("Confirm Password");

    }

    // Student number prompt
    public static void studentNumber() {

        prompt("Student Number");

    }

    // Employee ID prompt
    public static void employeeId() {

        prompt("Employee ID");

    }

    // Course code prompt
    public static void courseCode() {

        prompt("Course Code");

    }

    // Course name prompt
    public static void courseName() {

        prompt("Course Name");

    }

    // Department prompt
    public static void department() {

        prompt("Department");

    }

    // Email prompt
    public static void email() {

        prompt("Email");

    }
}

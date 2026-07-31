package com.joysistvi.univenrollmentapp.utils;

// Utility Class
// Provides standardized console prompts throughout the application
public final class PromptUtils {

    // Prevent instantiation
    private PromptUtils() {
    }

    // Generic prompt
    public static void prompt(String fieldName) {

        System.out.print(fieldName + ": ");

    }

    // Menu prompt
    public static void menuChoice() {

        System.out.print("\nEnter your choice: ");

    }

    // ==========================================================
    // ACCOUNT
    // ==========================================================

    public static void username() {
        prompt("Username");
    }

    public static void password() {
        prompt("Password");
    }

    public static void confirmPassword() {
        prompt("Confirm Password");
    }

    // ==========================================================
    // STUDENT
    // ==========================================================

    public static void studentNumber() {
        prompt("Student Number");
    }

    public static void firstName() {
        prompt("First Name");
    }

    public static void lastName() {
        prompt("Last Name");
    }

    public static void email() {
        prompt("Email");
    }

    // ==========================================================
    // EMPLOYEE
    // ==========================================================

    public static void employeeId() {
        prompt("Employee ID");
    }

    // ==========================================================
    // COURSE
    // ==========================================================

    public static void courseCode() {
        prompt("Course Code");
    }

    public static void courseName() {
        prompt("Course Name");
    }

    public static void department() {
        prompt("Department");
    }

    public static void units() {
        prompt("Units");
    }

    // ==========================================================
    // SEARCH
    // ==========================================================

    public static void keyword() {
        prompt("Search");
    }

    // ==========================================================
    // CONFIRMATION
    // ==========================================================

    public static void confirmation() {
        System.out.print("Confirm (Y/N): ");
    }
}

package com.joysistvi.univenrollmentapp.utils;

import java.util.Scanner;

// Utility Class
// Provides reusable console helper methods
public final class ConsoleUtils {

    // Prevent instantiation
    private ConsoleUtils() {
    }

    // Wait for the user to press Enter
    public static void pressEnterToContinue(Scanner input) {

        System.out.print("\nPress Enter to continue...");
        input.nextLine();

    }

    // Print a formatted header
    public static void printHeader(String title) {

        System.out.println("\n========================================");
        System.out.println("      " + title.toUpperCase());
        System.out.println("========================================");

    }

    // Print a success message
    public static void printSuccess(String message) {
        System.out.println("\n[SUCCESS] " + message);
    }

    // Print an error message
    public static void printError(String message) {
        System.out.println("\n[ERROR] " + message);
    }

    // Print a warning message
    public static void printWarning(String message) {
        System.out.println("\n[WARNING] " + message);
    }
}

package com.joysistvi.univenrollmentapp.utils;

import java.util.Scanner;

// Utility Class
// Provides reusable methods for validating console input
public final class InputValidator {

    // Prevent instantiation
    private InputValidator() {
    }

    // Read and validate a menu option
    public static int readMenuChoice(Scanner input) {

        while (!input.hasNextInt()) {

            System.out.println("\nError: Please enter a valid menu number.\n");
            input.nextLine();

            System.out.print("Enter your choice: ");

        }

        int choice = input.nextInt();
        input.nextLine();

        return choice;

    }

    // Read and validate a positive integer
    public static int readPositiveInt(Scanner input, String fieldName) {

        while (true) {

            System.out.print(fieldName + ": ");

            while (!input.hasNextInt()) {

                System.out.println("\nError: " + fieldName + " must be a number.\n");
                input.nextLine();

                System.out.print(fieldName + ": ");

            }

            int value = input.nextInt();
            input.nextLine();

            if (value > 0) {
                return value;
            }

            System.out.println("\nError: " + fieldName + " must be greater than zero.\n");

        }

    }

    // Read and validate a non-empty string
    public static String readRequiredString(Scanner input, String fieldName) {

        String value;

        do {

            System.out.print(fieldName + ": ");
            value = input.nextLine().trim();

            if (value.isEmpty()) {

                System.out.println("\nError: " + fieldName + " cannot be empty.\n");

            }

        } while (value.isEmpty());

        return value;

    }

    // Read and validate an email address
    public static String readEmail(Scanner input) {

        while (true) {

            System.out.print("Email: ");
            String email = input.nextLine().trim();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }

            System.out.println("\nError: Please enter a valid email address.\n");

        }

    }

    // Read and validate a yes/no confirmation
    public static boolean confirmAction(Scanner input, String message) {

        System.out.println(message);
        System.out.print("Press 'Y' to confirm or any other key to cancel: ");

        return input.nextLine().equalsIgnoreCase("Y");

    }
}

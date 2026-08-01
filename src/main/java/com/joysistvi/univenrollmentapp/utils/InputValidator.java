package com.joysistvi.univenrollmentapp.utils;

import java.util.Scanner;

// Utility Class
// Provides reusable methods for validating console input
public final class InputValidator {

    // Prevent instantiation
    private InputValidator() {
    }

    // ==========================================================
    // MENU INPUT
    // ==========================================================

    // Reads and validates a menu choice
    public static int readMenuChoice(Scanner input) {

        while (true) {

            System.out.print("Enter your choice: ");

            if (input.hasNextInt()) {

                int choice = input.nextInt();
                input.nextLine();

                return choice;

            }

            MessagePrinter.error("Please enter a valid menu number.");

            input.nextLine();

        }

    }

    // Reads and validates a menu choice within a range
    public static int readMenuChoice(
            Scanner input,
            int min,
            int max) {

        while (true) {

            int choice = readMenuChoice(input);

            if (choice >= min && choice <= max) {
                return choice;
            }

            MessagePrinter.error(
                    "Please enter a number between "
                            + min + " and " + max + ".");

        }

    }

    // ==========================================================
    // INTEGER INPUT
    // ==========================================================

    // Reads and validates a positive integer
    public static int readPositiveInt(
            Scanner input,
            String fieldName) {

        while (true) {

            System.out.print(fieldName + ": ");

            if (!input.hasNextInt()) {

                MessagePrinter.error(fieldName + " must be a number.");

                input.nextLine();

                continue;

            }

            int value = input.nextInt();
            input.nextLine();

            if (value > 0) {
                return value;
            }

            MessagePrinter.error(
                    fieldName + " must be greater than zero.");

        }

    }

    // ==========================================================
    // STRING INPUT
    // ==========================================================

    // Reads and validates a required string
    public static String readRequiredString(
            Scanner input,
            String fieldName) {

        while (true) {

            System.out.print(fieldName + ": ");

            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            MessagePrinter.error(
                    fieldName + " cannot be empty.");

        }

    }

    // Reads an optional string
    public static String readOptionalString(
            Scanner input,
            String fieldName) {

        System.out.print(fieldName + ": ");

        return input.nextLine().trim();

    }

    // ==========================================================
    // EMAIL INPUT
    // ==========================================================

    // Reads and validates an email address
    public static String readEmail(Scanner input) {

        while (true) {

            System.out.print("Email: ");

            String email = input.nextLine().trim();

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                return email;

            }

            MessagePrinter.error(
                    "Please enter a valid email address.");

        }

    }

    // ==========================================================
    // PASSWORD INPUT
    // ==========================================================

    // Reads a password
    public static String readPassword(Scanner input) {

        System.out.print("Password: ");

        return input.nextLine();

    }

    // Reads and confirms a password
    public static String readConfirmedPassword(
            Scanner input) {

        while (true) {

            String password = readPassword(input);

            System.out.print("Confirm Password: ");

            String confirmPassword = input.nextLine();

            if (password.equals(confirmPassword)) {

                return password;

            }

            MessagePrinter.error(
                    "Passwords do not match.");

        }

    }

    // ==========================================================
    // CONFIRMATION
    // ==========================================================

    // Reads a yes/no confirmation
    public static boolean confirmAction(
            Scanner input,
            String message) {

        while (true) {

            System.out.print(message + " (Y/N): ");

            String choice = input.nextLine().trim();

            if (choice.equalsIgnoreCase("Y")) {

                return true;

            }

            if (choice.equalsIgnoreCase("N")) {

                return false;

            }

            MessagePrinter.error(
                    "Please enter Y or N.");

        }

    }
}

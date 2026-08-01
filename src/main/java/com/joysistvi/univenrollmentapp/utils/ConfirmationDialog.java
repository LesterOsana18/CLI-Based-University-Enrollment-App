package com.joysistvi.univenrollmentapp.utils;

import java.util.Scanner;

// Utility Class
// Provides reusable confirmation dialogs
public final class ConfirmationDialog {

    // Prevent instantiation
    private ConfirmationDialog() {
    }

    // Displays a confirmation prompt
    public static boolean confirm(
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

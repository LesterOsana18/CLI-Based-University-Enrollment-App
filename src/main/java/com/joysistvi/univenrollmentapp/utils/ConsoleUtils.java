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
}

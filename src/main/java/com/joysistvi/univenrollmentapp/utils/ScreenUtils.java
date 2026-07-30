package com.joysistvi.univenrollmentapp.utils;

// Utility Class
// Provides reusable methods for screen-related operations
public final class ScreenUtils {

    // Prevent instantiation
    private ScreenUtils() {
    }

    // Clear the console screen
    public static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();

    }
}

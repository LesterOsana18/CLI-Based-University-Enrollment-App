package com.joysistvi.univenrollmentapp.utils;

// Utils
// Provides reusable console borders and spacing utilities
public final class BorderPrinter {

    // Prevent instantiation
    private BorderPrinter() {
    }

    // Prints a standard divider
    public static void line() {

        System.out.println(
                "------------------------------------------------------------");

    }

    // Prints a thicker divider
    public static void doubleLine() {

        System.out.println(
                "============================================================");

    }

    // Prints a blank line
    public static void blankLine() {

        System.out.println();

    }

    // Prints a divider using a custom character
    public static void repeat(char character, int length) {

        for (int i = 0; i < length; i++) {

            System.out.print(character);

        }

        System.out.println();

    }
}

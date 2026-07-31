package com.joysistvi.univenrollmentapp.utils;

// Utility Class
// Provides reusable methods for displaying console menus
public final class MenuPrinter {

    // Prevent instantiation
    private MenuPrinter() {
    }

    // Prints a menu with numbered options
    public static void printMenu(
            String title,
            String... options) {

        HeaderPrinter.printHeader(title);

        for (int i = 0; i < options.length; i++) {

            System.out.printf("%d. %s%n",
                    i + 1,
                    options[i]);

        }

        System.out.println("0. Logout");

        BorderPrinter.doubleLine();

    }

    // Prints a menu without automatically adding Logout
    public static void printMenuWithExit(
            String title,
            String... options) {

        HeaderPrinter.printHeader(title);

        for (int i = 0; i < options.length; i++) {

            System.out.printf("%d. %s%n",
                    i + 1,
                    options[i]);

        }

        BorderPrinter.doubleLine();

    }
}

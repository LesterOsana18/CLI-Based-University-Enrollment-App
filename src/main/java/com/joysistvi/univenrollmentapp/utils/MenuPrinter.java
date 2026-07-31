package com.joysistvi.univenrollmentapp.utils;

// Utility Class
// Provides reusable methods for displaying console menus
public final class MenuPrinter {

    // Prevent instantiation
    private MenuPrinter() {
    }

    // Prints a menu with a customizable zero option
    public static void printMenu(
            String title,
            String zeroOption,
            String... options) {

        HeaderPrinter.printHeader(title);

        for (int i = 0; i < options.length; i++) {

            System.out.printf("%d. %s%n",
                    i + 1,
                    options[i]);

        }

        System.out.printf("0. %s%n", zeroOption);

        BorderPrinter.doubleLine();

    }
}

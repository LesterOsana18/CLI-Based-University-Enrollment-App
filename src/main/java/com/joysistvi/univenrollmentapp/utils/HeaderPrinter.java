package com.joysistvi.univenrollmentapp.utils;

import com.joysistvi.univenrollmentapp.session.Session;

// Utils
// Provides standardized application headers for all console screens
public final class HeaderPrinter {

    // Prevent instantiation
    private HeaderPrinter() {
    }

    // Prints a simple screen header
    public static void printHeader(String title) {

        BorderPrinter.doubleLine();

        System.out.printf("%40s%n", title);

        BorderPrinter.doubleLine();
    }

    // Prints a screen header with a subtitle
    public static void printHeader(String title, String subtitle) {

        BorderPrinter.doubleLine();

        System.out.printf("%40s%n", title);

        BorderPrinter.line();

        System.out.printf("%40s%n", subtitle);

        BorderPrinter.doubleLine();
    }

    // Prints the dashboard header for authenticated users
    public static void printDashboard(String dashboardTitle) {

        BorderPrinter.doubleLine();

        System.out.printf("%40s%n",
                "UNIVERSITY ENROLLMENT SYSTEM");

        BorderPrinter.line();

        System.out.printf("%40s%n",
                dashboardTitle);

        BorderPrinter.doubleLine();

        if (Session.isLoggedIn()) {

            System.out.printf("Logged in as : %s%n",
                    Session.getCurrentUser().getUsername());

            System.out.printf("Role         : %s%n",
                    Session.getCurrentUser().getRole());

            BorderPrinter.doubleLine();

        }

    }
}

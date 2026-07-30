package com.joysistvi.univenrollmentapp.utils;

// Utility Class
// Provides reusable methods for formatting console tables
public final class TableFormatter {

    // Prevent instantiation
    private TableFormatter() {
    }

    // Print a table border
    public static void printBorder(String border) {
        System.out.println(border);
    }

    // Print the total number of records
    public static void printTotalRecords(String label, int total) {
        System.out.println("Total " + label + ": " + total);
    }

    // Print a message when no records are found
    public static void printNoRecordsFound(String label) {
        System.out.println("\nNo " + label + " found.\n");
    }

    // Print a divider
    public static void printDivider() {
        System.out.println("----------------------------------------");
    }
}

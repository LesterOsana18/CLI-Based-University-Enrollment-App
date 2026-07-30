package com.joysistvi.univenrollmentapp.enums;

// Enum
// Defines the valid semester types in the system
public enum Semester {

    // Enum constants
    FIRST("1st Semester"),
    SECOND("2nd Semester"),
    SUMMER("Summer");

    // Display name
    private final String displayName;

    // Constructor
    Semester(String displayName) {
        this.displayName = displayName;
    }

    // Getter (Accessor)
    public String getDisplayName() {
        return displayName;
    }

    // Return the display name when printed
    @Override
    public String toString() {
        return displayName;
    }
}

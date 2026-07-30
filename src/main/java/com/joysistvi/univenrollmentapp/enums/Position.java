package com.joysistvi.univenrollmentapp.enums;

// Enum
// Defines the valid employee positions in the system
public enum Position {

    // Enum constants
    REGISTRAR("Registrar"),
    ADMIN("Administrator");

    // Display name
    private final String displayName;

    // Constructor
    Position(String displayName) {
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

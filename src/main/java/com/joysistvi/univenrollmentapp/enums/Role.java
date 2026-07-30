package com.joysistvi.univenrollmentapp.enums;

// Enum
// Defines the valid user roles in the system
public enum Role {

    // Enum constants
    STUDENT("Student"),
    REGISTRAR("Registrar"),
    ADMIN("Administrator");

    // Display name
    private final String displayName;

    // Constructor
    Role(String displayName) {
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

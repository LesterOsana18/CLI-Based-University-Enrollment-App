package com.joysistvi.univenrollmentapp.enums;

// Enum
// Defines the valid status types in the system
public enum Status {

    // Enum constants
    ACTIVE("Active"),
    INACTIVE("Inactive");

    // Display name
    private final String displayName;

    // Constructor
    Status(String displayName) {
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

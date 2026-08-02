package com.joysistvi.univenrollmentapp.model;

// Model Class
// Stores the information of a Department object using encapsulation
public class Department {

    // Attributes
    private final int id;
    private final String departmentName;

    // Constructor
    public Department(
            int id,
            String departmentName) {

        this.id = id;
        this.departmentName = departmentName;

    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

}
package com.joysistvi.univenrollmentapp.model;

import com.joysistvi.univenrollmentapp.enums.Status;

// Model Class
// Stores the information of a Student object using encapsulation
public class Student {

    // Private fields (Encapsulation)
    private final int id;
    private final String studentNumber;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final int departmentId;
    private final String departmentName;
    private final int userId;
    private final String username;
    private final Status status;

    // Constructor for creating a Student without a department name
    public Student(
            int id,
            String studentNumber,
            String firstName,
            String lastName,
            String email,
            int departmentId,
            int userId,
            String username,
            Status status) {

        this(
                id,
                studentNumber,
                firstName,
                lastName,
                email,
                departmentId,
                null,
                userId,
                username,
                status);

    }

    // Constructor for creating a Student with a department name
    public Student(
            int id,
            String studentNumber,
            String firstName,
            String lastName,
            String email,
            int departmentId,
            String departmentName,
            int userId,
            String username,
            Status status) {

        this.id = id;
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.userId = userId;
        this.username = username;
        this.status = status;

    }

    // Getters
    public int getId() {
        return id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Status getStatus() {
        return status;
    }

}
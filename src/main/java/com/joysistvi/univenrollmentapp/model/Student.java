package com.joysistvi.univenrollmentapp.model;

import com.joysistvi.univenrollmentapp.enums.Status;

// Model Class
// Stores the information of a Student object using encapsulation
public class Student {

    // Private fields (Encapsulation)
    private  int id;
    private  String studentNumber;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  int departmentId;
    private  String departmentName;
    private  int userId;
    private  String username;
    private  Status status;

    // Constructor
    public Student() {
        // Default constructor
    }

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


    // Setters (if needed, but not provided in this case)
    public void setId(int id) {
        this.id = id;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
package com.joysistvi.univenrollmentapp.model;

import com.joysistvi.univenrollmentapp.enums.Status;

// Model Class
// Stores the information of a Student object using encapsulation
public class Student {

    // Private fields (Encapsulation)
    private int id;
    private String studentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private int departmentId;
    private int userId;
    private Status status;

    // Default constructor
    public Student() {
    }

    // Constructor for existing records (includes ID)
    public Student(int id, String studentNumber, String firstName, String lastName,
            String email, int departmentId, int userId, Status status) {

        this.id = id;
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.userId = userId;
        this.status = status;

    }

    // Constructor for creating a new student (no ID yet)
    public Student(String studentNumber, String firstName, String lastName,
            String email, int departmentId, int userId) {

        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.userId = userId;
        this.status = Status.ACTIVE;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
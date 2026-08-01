package com.joysistvi.univenrollmentapp.model;

import com.joysistvi.univenrollmentapp.enums.Status;

public class Student {
    private final int id;
    private final String studentNumber;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final int departmentId;
    private final String departmentName;
    private final Status status;

    public Student(int id, String studentNumber, String firstName, String lastName,
            String email, int departmentId, String departmentName, Status status) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.status = status;
    }

    public int getId() { return id; }
    public String getStudentNumber() { return studentNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public int getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }
    public Status getStatus() { return status; }
}

package com.joysistvi.univenrollmentapp.model;

import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;

public class Employee {
    private final int id;
    private final String employeeId;
    private final String firstName;
    private final String lastName;
    private final Position position;
    private final int userId;
    private final String username;
    private final Status status;

    public Employee(int id, String employeeId, String firstName, String lastName,
            Position position, int userId, String username, Status status) {
        this.id = id;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.userId = userId;
        this.username = username;
        this.status = status;
    }

    public int getId() { return id; }
    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Position getPosition() { return position; }
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public Status getStatus() { return status; }
}

package com.joysistvi.univenrollmentapp.model;

// Model Class
// Stores the information of a Course object using encapsulation
public class Course {

    private final int id;
    private final String courseCode;
    private final String courseName;
    private final int units;
    private final int departmentId;
    private final String departmentName;

    public Course(int id, String courseCode, String courseName, int units, int departmentId) {
        this(id, courseCode, courseName, units, departmentId, null);
    }

    public Course(int id, String courseCode, String courseName, int units, int departmentId, String departmentName) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.units = units;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getUnits() {
        return units;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
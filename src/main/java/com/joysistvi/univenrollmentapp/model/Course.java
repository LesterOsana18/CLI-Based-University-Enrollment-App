package com.joysistvi.univenrollmentapp.model;

// Model Class
// Stores the information of a Course object using encapsulation
public class Course {

    // Private fields (Encapsulation)
    private int id;
    private String courseCode;
    private String courseName;
    private int units;
    private int departmentId;

    // Default constructor
    public Course() {
    }

    // Constructor for existing records (includes ID)
    public Course(int id, String courseCode, String courseName,
            int units, int departmentId) {

        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.units = units;
        this.departmentId = departmentId;

    }

    // Constructor for creating a new course (no ID yet)
    public Course(String courseCode, String courseName,
            int units, int departmentId) {

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.units = units;
        this.departmentId = departmentId;

    }

    // ==========================================================
    // Getter (Accessor) and Setter (Mutator) Methods
    // ==========================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
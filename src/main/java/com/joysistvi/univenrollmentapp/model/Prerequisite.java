package com.joysistvi.univenrollmentapp.model;

// Model Class
// Stores the information of a Prerequisite object using encapsulation
public class Prerequisite {

    // Private fields (Encapsulation)
    private int id;
    private int courseId;
    private String courseCode;
    private String courseName;
    private int prerequisiteCourseId;
    private String prerequisiteCourseCode;
    private String prerequisiteCourseName;

    // Default constructor
    public Prerequisite() {
    }

    // Constructor for existing records (includes ID)
    public Prerequisite(int id, int courseId, int prerequisiteCourseId) {
        this.id = id;
        this.courseId = courseId;
        this.prerequisiteCourseId = prerequisiteCourseId;
    }

    // Constructor for creating a new prerequisite link (no ID yet)
    public Prerequisite(int courseId, int prerequisiteCourseId) {
        this.courseId = courseId;
        this.prerequisiteCourseId = prerequisiteCourseId;
    }

    // Constructor for full details from joins
    public Prerequisite(
            int id,
            int courseId,
            String courseCode,
            String courseName,
            int prerequisiteCourseId,
            String prerequisiteCourseCode,
            String prerequisiteCourseName) {
        this.id = id;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.prerequisiteCourseId = prerequisiteCourseId;
        this.prerequisiteCourseCode = prerequisiteCourseCode;
        this.prerequisiteCourseName = prerequisiteCourseName;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int getPrerequisiteCourseId() {
        return prerequisiteCourseId;
    }

    public void setPrerequisiteCourseId(int prerequisiteCourseId) {
        this.prerequisiteCourseId = prerequisiteCourseId;
    }

    public String getPrerequisiteCourseCode() {
        return prerequisiteCourseCode;
    }

    public void setPrerequisiteCourseCode(String prerequisiteCourseCode) {
        this.prerequisiteCourseCode = prerequisiteCourseCode;
    }

    public String getPrerequisiteCourseName() {
        return prerequisiteCourseName;
    }

    public void setPrerequisiteCourseName(String prerequisiteCourseName) {
        this.prerequisiteCourseName = prerequisiteCourseName;
    }
}
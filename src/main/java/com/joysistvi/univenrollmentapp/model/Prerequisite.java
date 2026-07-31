package com.joysistvi.univenrollmentapp.model;

// Model Class
// Stores the information of a Prerequisite object using encapsulation
public class Prerequisite {

    // Private fields (Encapsulation)
    private int id;
    private int courseId;
    private int prerequisiteCourseId;

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

    public int getPrerequisiteCourseId() {
        return prerequisiteCourseId;
    }

    public void setPrerequisiteCourseId(int prerequisiteCourseId) {
        this.prerequisiteCourseId = prerequisiteCourseId;
    }
}
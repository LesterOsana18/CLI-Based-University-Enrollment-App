package com.joysistvi.univenrollmentapp.model;

// Model Class
// Stores the information of a Prerequisite object
public class Prerequisite {

    // Private fields (Encapsulation)
    private final int id;
    private final int courseId;
    private final String courseCode;
    private final String courseName;
    private final int prerequisiteCourseId;
    private final String prerequisiteCourseCode;
    private final String prerequisiteCourseName;

    // Constructor for creating a new prerequisite
    public Prerequisite(int courseId, int prerequisiteCourseId) {
        this(
                0,
                courseId,
                null,
                null,
                prerequisiteCourseId,
                null,
                null);
    }

    // Constructor for existing prerequisite records
    public Prerequisite(
            int id,
            int courseId,
            int prerequisiteCourseId) {

        this(
                id,
                courseId,
                null,
                null,
                prerequisiteCourseId,
                null,
                null);
    }

    // Constructor for joined query results
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
    // Getter (Accessor) Methods
    // ==========================================================

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getPrerequisiteCourseId() {
        return prerequisiteCourseId;
    }

    public String getPrerequisiteCourseCode() {
        return prerequisiteCourseCode;
    }

    public String getPrerequisiteCourseName() {
        return prerequisiteCourseName;
    }

}
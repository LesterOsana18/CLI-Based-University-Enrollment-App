package com.joysistvi.univenrollmentapp.model;

public class Prerequisite {
    private final int id;
    private final int courseId;
    private final String courseCode;
    private final String courseName;
    private final int prerequisiteCourseId;
    private final String prerequisiteCourseCode;
    private final String prerequisiteCourseName;

    public Prerequisite(int id, int courseId, int prerequisiteCourseId) {
        this(id, courseId, null, null, prerequisiteCourseId, null, null);
    }

    public Prerequisite(int id, int courseId, String courseCode, String courseName,
            int prerequisiteCourseId, String prerequisiteCourseCode, String prerequisiteCourseName) {
        this.id = id;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.prerequisiteCourseId = prerequisiteCourseId;
        this.prerequisiteCourseCode = prerequisiteCourseCode;
        this.prerequisiteCourseName = prerequisiteCourseName;
    }

    public int getId() { return id; }
    public int getCourseId() { return courseId; }
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getPrerequisiteCourseId() { return prerequisiteCourseId; }
    public String getPrerequisiteCourseCode() { return prerequisiteCourseCode; }
    public String getPrerequisiteCourseName() { return prerequisiteCourseName; }
}

package com.joysistvi.univenrollmentapp.model;

import java.sql.Date;

import com.joysistvi.univenrollmentapp.enums.Semester;

// Model Class
// Stores the information of an Enrollment object using encapsulation
public class Enrollment {

    // Enrollment information
    private final int id;
    private final int studentId;
    private final String studentNumber;
    private final String studentName;
    private final int courseId;
    private final String courseCode;
    private final String courseName;
    private final String schoolYear;
    private final Semester semester;
    private final Date dateEnrolled;

    // Constructor for creating a new enrollment
    public Enrollment(
            int studentId,
            int courseId,
            String schoolYear,
            Semester semester) {

        this(
                0,
                studentId,
                null,
                null,
                courseId,
                null,
                null,
                schoolYear,
                semester,
                null);

    }

    // Constructor for existing records
    public Enrollment(
            int id,
            int studentId,
            String studentNumber,
            String studentName,
            int courseId,
            String courseCode,
            String courseName,
            String schoolYear,
            Semester semester,
            Date dateEnrolled) {

        this.id = id;
        this.studentId = studentId;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.schoolYear = schoolYear;
        this.semester = semester;
        this.dateEnrolled = dateEnrolled;

    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getStudentName() {
        return studentName;
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

    public String getSchoolYear() {
        return schoolYear;
    }

    public Semester getSemester() {
        return semester;
    }

    public Date getDateEnrolled() {
        return dateEnrolled;
    }

}
package com.joysistvi.univenrollmentapp.model;

import java.sql.Date;

import com.joysistvi.univenrollmentapp.enums.Semester;

/**
 * Model class for Enrollment.
 * Stores enrollment information using encapsulation.
 */
public class Enrollment {

    private int id;
    private int studentId;
    private String studentNumber;
    private String studentName;
    private int courseId;
    private String courseCode;
    private String courseName;
    private String schoolYear;
    private Semester semester;
    private Date dateEnrolled;

    public Enrollment() {
        // Default constructor
    }

    public Enrollment(int studentId, int courseId, String schoolYear, Semester semester) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.schoolYear = schoolYear;
        this.semester = semester;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Date getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(Date dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }
}
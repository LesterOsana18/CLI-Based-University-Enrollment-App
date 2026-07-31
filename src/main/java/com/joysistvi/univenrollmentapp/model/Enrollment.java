package com.joysistvi.univenrollmentapp.model;

import java.sql.Date;

import com.joysistvi.univenrollmentapp.enums.Semester;

// Model Class
// Stores the information of an Enrollment object using encapsulation
public class Enrollment {

    // Private fields (Encapsulation)
    private int id;
    private int studentId;
    private int courseId;
    private String schoolYear;
    private Semester semester;
    private Date dateEnrolled;

    // Default constructor
    public Enrollment() {
    }

    // Constructor for existing records (includes ID)
    public Enrollment(int id, int studentId, int courseId,
            String schoolYear, Semester semester, Date dateEnrolled) {

        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.schoolYear = schoolYear;
        this.semester = semester;
        this.dateEnrolled = dateEnrolled;

    }

    // Constructor for creating a new enrollment (no ID yet, date defaults in DB)
    public Enrollment(int studentId, int courseId,
            String schoolYear, Semester semester) {

        this.studentId = studentId;
        this.courseId = courseId;
        this.schoolYear = schoolYear;
        this.semester = semester;

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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
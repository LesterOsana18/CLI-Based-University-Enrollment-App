package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.service.EnrollmentService;

// Controller Class
// Handles requests related to enrollment management
public class EnrollmentController {

    // Dependency Injection
    private final EnrollmentService enrollmentService;

    // Constructor
    public EnrollmentController(
            EnrollmentService enrollmentService) {

        this.enrollmentService = enrollmentService;

    }

    // Retrieve all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Retrieve enrollment history of a student
    public List<Enrollment> getEnrollmentHistory(int studentId) {
        return enrollmentService.getEnrollmentHistory(studentId);
    }

    // Enroll a student
    public String enrollStudent(
            int studentId,
            int courseId,
            String schoolYear,
            Semester semester) {

        return enrollmentService.enrollStudent(
                studentId,
                courseId,
                schoolYear,
                semester);

    }

    // Drop an enrollment
    public boolean dropEnrollment(
            int enrollmentId,
            int studentId) {

        return enrollmentService.dropEnrollment(
                enrollmentId,
                studentId);

    }

    // Search enrollments
    public List<Enrollment> searchEnrollments(String keyword) {
        return enrollmentService.searchEnrollments(keyword);
    }

}
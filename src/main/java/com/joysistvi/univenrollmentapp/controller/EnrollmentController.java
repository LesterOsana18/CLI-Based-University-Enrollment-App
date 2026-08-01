package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

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

    // Search enrollments
    public List<Enrollment> searchEnrollments(String keyword) {
        return enrollmentService.searchEnrollments(keyword);
    }

}
package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Enrollment;
import java.util.List;

// import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
// import com.joysistvi.univenrollmentapp.model.Enrollment;

// Service Interface
// Defines the business operations for Enrollment objects
public interface EnrollmentService {

    // Retrieve a student's enrollment history
    List<Enrollment> getEnrollmentHistory(int studentId);

    // Enroll a student in a course for a given term
    // Returns a result message explaining success or the specific reason for failure
    String enrollStudent(
            int studentId, int courseId, String schoolYear, Semester semester);

    // Drop a student's enrollment (only if it belongs to that student)
    boolean dropEnrollment(int enrollmentId, int studentId);
}
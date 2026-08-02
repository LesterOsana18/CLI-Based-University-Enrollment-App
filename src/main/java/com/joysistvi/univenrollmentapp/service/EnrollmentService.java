package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Enrollment;

// Service Interface
// Defines the business operations for Enrollment management
public interface EnrollmentService {

    // Retrieve all enrollments
    List<Enrollment> getAllEnrollments();

    // Retrieve archived enrollments
    List<Enrollment> getArchivedEnrollments();

    // Search enrollments
    List<Enrollment> searchEnrollments(String keyword);

    // Retrieve a student's enrollment history
    List<Enrollment> getEnrollmentHistory(int studentId);

    // Retrieve an enrollment by ID
    Enrollment getEnrollmentById(int id);

    // Enroll a student in a course
    String enrollStudent(
            int studentId,
            int courseId,
            String schoolYear,
            Semester semester);

        // Drop an enrollment for a student
        boolean dropEnrollment(int enrollmentId, int studentId);

    // Archive an enrollment
    boolean archiveEnrollment(int id);

    // Restore an archived enrollment
    boolean restoreEnrollment(int id);

    // Permanently delete an enrollment
    boolean deleteEnrollment(int id);

}
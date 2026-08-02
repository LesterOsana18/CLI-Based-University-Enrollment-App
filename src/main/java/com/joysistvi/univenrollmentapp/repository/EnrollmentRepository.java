package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Enrollment;

// Repository Interface
// Defines the database operations for Enrollment objects
public interface EnrollmentRepository {

    // Retrieve all enrollments
    List<Enrollment> getAllEnrollments();

    // Retrieve all archived enrollments
    List<Enrollment> getArchivedEnrollments();

    // Search enrollments
    List<Enrollment> searchEnrollments(String keyword);

    // Retrieve all enrollments of a student
    List<Enrollment> getEnrollmentHistory(int studentId);

    // Retrieve an enrollment by ID
    Enrollment getEnrollmentById(int id);

    // Check if the student is already enrolled
    boolean enrollmentExists(
            int studentId,
            int courseId,
            String schoolYear,
            Semester semester);

    // Create a new enrollment
    boolean createEnrollment(Enrollment enrollment);

    // Permanently delete an enrollment
    boolean deleteEnrollment(int id);

}
package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Enrollment;

// Repository Interface
// Defines the database operations for Enrollment objects
public interface EnrollmentRepository {

    // Retrieve all enrollments
    List<Enrollment> getAllEnrollments();

    // Search enrollments by keyword
    List<Enrollment> searchEnrollments(String keyword);

    // Retrieve all enrollments of a specific student
    List<Enrollment> findByStudentId(int studentId);

    // Retrieve a single enrollment by ID
    Enrollment findById(int id);

    // Check if a student is already enrolled in a course for a given term
    boolean existsByStudentCourseTerm(
            int studentId,
            int courseId,
            String schoolYear,
            Semester semester);

    // Insert a new enrollment record
    boolean save(Enrollment enrollment);

    // Archive an enrollment
    boolean archive(int id);

    // Restore an archived enrollment
    boolean restore(int id);

    // Permanently delete an enrollment
    boolean delete(int id);

}
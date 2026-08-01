package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Enrollment;

// Service Interface
// Defines the business operations for Enrollment management
public interface EnrollmentService {

   // Retrieve all enrollments
   List<Enrollment> getAllEnrollments();

   // Search enrollments by keyword
   List<Enrollment> searchEnrollments(String keyword);

   // Retrieve a student's enrollment history
   List<Enrollment> getEnrollmentHistory(int studentId);

   // Enroll a student into a course
   String enrollStudent(
        int studentId,
        int courseId,
        String schoolYear,
        Semester semester);

   // Drop an enrollment
   boolean dropEnrollment(
        int enrollmentId,
        int studentId);

}
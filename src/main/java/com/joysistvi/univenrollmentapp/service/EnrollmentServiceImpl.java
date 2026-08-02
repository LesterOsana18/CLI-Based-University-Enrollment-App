package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.repository.EnrollmentRepository;
import com.joysistvi.univenrollmentapp.repository.StudentRepository;

// Service Implementation
// Implements the business operations for Enrollment objects
public class EnrollmentServiceImpl implements EnrollmentService {

    // Dependency Injection
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final PrerequisiteService prerequisiteService;

    // Constructor
    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            PrerequisiteService prerequisiteService) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.prerequisiteService = prerequisiteService;

    }

    // Retrieve all enrollments
    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.getAllEnrollments();
    }

    // Retrieve archived enrollments
    @Override
    public List<Enrollment> getArchivedEnrollments() {
        return enrollmentRepository.getArchivedEnrollments();
    }

    // Search enrollments
    @Override
    public List<Enrollment> searchEnrollments(String keyword) {
        return enrollmentRepository.searchEnrollments(keyword);
    }

    // Retrieve enrollment by ID
    @Override
    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.getEnrollmentById(id);
    }

    // Retrieve a student's enrollment history
    @Override
    public List<Enrollment> getEnrollmentHistory(int studentId) {
        return enrollmentRepository.getEnrollmentHistory(studentId);
    }

    // Enroll a student
    @Override
    public String enrollStudent(
            int studentId,
            int courseId,
            String schoolYear,
            Semester semester) {

        Student student = studentRepository.getStudentById(studentId);

        if (student == null) {
            return "Student record not found.";
        }

        if (student.getStatus() != Status.ACTIVE) {
            return "Only active students may enroll in courses.";
        }

        if (enrollmentRepository.enrollmentExists(
                studentId,
                courseId,
                schoolYear,
                semester)) {

            return "You are already enrolled in this course for the selected term.";

        }

        List<Prerequisite> prerequisites =
                prerequisiteService.getAllPrerequisites();

        List<Enrollment> history =
                enrollmentRepository.getEnrollmentHistory(studentId);

        for (Prerequisite prerequisite : prerequisites) {

            if (prerequisite.getCourseId() != courseId) {
                continue;
            }

            boolean completed = history.stream()
                    .anyMatch(enrollment ->
                            enrollment.getCourseId()
                                    == prerequisite.getPrerequisiteCourseId());

            if (!completed) {

                String courseLabel =
                        prerequisite.getPrerequisiteCourseCode() != null
                                ? prerequisite.getPrerequisiteCourseCode()
                                : "Course ID " + prerequisite.getPrerequisiteCourseId();

                return "Missing prerequisite: " + courseLabel;

            }
        }

        Enrollment enrollment = new Enrollment(
                studentId,
                courseId,
                schoolYear,
                semester);

        return enrollmentRepository.createEnrollment(enrollment)
                ? "Enrollment successful."
                : "Enrollment failed due to a database error.";
    }

    // Drop an enrollment
    @Override
    public boolean dropEnrollment(int enrollmentId, int studentId) {

        Enrollment enrollment =
                enrollmentRepository.getEnrollmentById(enrollmentId);

        if (enrollment == null
                || enrollment.getStudentId() != studentId) {

            return false;

        }

        return enrollmentRepository.deleteEnrollment(enrollmentId);
    }

    // Delete enrollment
    @Override
    public boolean deleteEnrollment(int id) {
        return enrollmentRepository.deleteEnrollment(id);
    }

}
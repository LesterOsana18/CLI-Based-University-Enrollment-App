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
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.getAllEnrollments();
    }

    // Search enrollments by keyword
    public List<Enrollment> searchEnrollments(String keyword) {
        return enrollmentRepository.searchEnrollments(keyword);
    }

    @Override
    public List<Enrollment> getEnrollmentHistory(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public String enrollStudent(
            int studentId, int courseId, String schoolYear, Semester semester) {

        // 1. Student must exist and be ACTIVE
        Student student = studentRepository.findById(studentId);

        if (student == null) {
            return "Student record not found.";
        }

        if (student.getStatus() != Status.ACTIVE) {
            return "Only active students may enroll in courses.";
        }

        // 2. Student must not already be enrolled in this course for this term
        boolean alreadyEnrolled =
                enrollmentRepository.existsByStudentCourseTerm(
                        studentId, courseId, schoolYear, semester);

        if (alreadyEnrolled) {
            return "You are already enrolled in this course for the selected term.";
        }

        // 3. All prerequisites of this course must already appear in the student's history
        List<Prerequisite> allPrerequisites =
                prerequisiteService.getAllPrerequisites();

        List<Enrollment> history =
                enrollmentRepository.findByStudentId(studentId);

        for (Prerequisite prerequisite : allPrerequisites) {

            if (prerequisite.getCourseId() != courseId) {
                continue;
            }

            boolean hasTakenPrerequisite = history.stream()
                    .anyMatch(e -> e.getCourseId()
                            == prerequisite.getPrerequisiteCourseId());

            if (!hasTakenPrerequisite) {

                String label = prerequisite.getPrerequisiteCourseCode() != null
                        ? prerequisite.getPrerequisiteCourseCode()
                        : ("Course ID " + prerequisite.getPrerequisiteCourseId());

                return "Missing prerequisite: " + label;

            }

        }

        // 4. All checks passed — create the enrollment
        Enrollment enrollment =
                new Enrollment(studentId, courseId, schoolYear, semester);

        boolean saved = enrollmentRepository.save(enrollment);

        return saved
                ? "Enrollment successful."
                : "Enrollment failed due to a database error.";

    }

    @Override
    public boolean dropEnrollment(int enrollmentId, int studentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);

        // Make sure this enrollment actually belongs to the requesting student
        if (enrollment == null || enrollment.getStudentId() != studentId) {
            return false;
        }

        return enrollmentRepository.delete(enrollmentId);

    }
}
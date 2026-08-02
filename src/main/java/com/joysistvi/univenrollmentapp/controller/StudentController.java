package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.service.CourseService;
import com.joysistvi.univenrollmentapp.service.EnrollmentService;
import com.joysistvi.univenrollmentapp.service.PrerequisiteService;
import com.joysistvi.univenrollmentapp.service.StudentService;

// Controller Class
// Acts as the bridge between the View and the Service layer
// for the Student module
public class StudentController {

    // Dependency Injection
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final PrerequisiteService prerequisiteService;

    // Constructor
    public StudentController(
            StudentService studentService,
            CourseService courseService,
            EnrollmentService enrollmentService,
            PrerequisiteService prerequisiteService) {

        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.prerequisiteService = prerequisiteService;
    }

    // ==========================================================
    // Student Management
    // ==========================================================

    // Retrieve all students
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Retrieve archived students
    public List<Student> getArchivedStudents() {
        return studentService.getArchivedStudents();
    }

    // Retrieve a student by ID
    public Student getStudentById(int id) {
        return studentService.getStudentById(id);
    }

    // Retrieve a student by user ID
    public Student getStudentByUserId(int userId) {
        return studentService.getStudentByUserId(userId);
    }

    // Create a new student
    public boolean createStudent(Student student, String password) {
        return studentService.createStudent(student, password);
    }

    // Update an existing student
    public boolean updateStudent(Student student) {
        return studentService.updateStudent(student);
    }

    // Archive a student
    public boolean archiveStudent(int id) {
        return studentService.archiveStudent(id);
    }

    // Restore an archived student
    public boolean restoreStudent(int id) {
        return studentService.restoreStudent(id);
    }

    // Permanently delete a student
    public boolean deleteStudent(int id) {
        return studentService.deleteStudent(id);
    }

    // Reset student password
    public boolean resetPassword(int userId, String newPassword) {
        return studentService.resetPassword(userId, newPassword);
    }

    // ==========================================================
    // Student Portal
    // ==========================================================

    // Retrieve all available courses
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Retrieve enrollment history
    public List<Enrollment> getEnrollmentHistory(int studentId) {
        return enrollmentService.getEnrollmentHistory(studentId);
    }

    // Enroll in a course
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

    // Retrieve all prerequisites
    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteService.getAllPrerequisites();
    }

}
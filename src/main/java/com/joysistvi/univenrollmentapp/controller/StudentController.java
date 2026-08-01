package com.joysistvi.univenrollmentapp.controller;

import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.service.StudentServiceImpl;
import java.util.List;

import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
// import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.service.CourseService;
import com.joysistvi.univenrollmentapp.service.EnrollmentService;
import com.joysistvi.univenrollmentapp.service.PrerequisiteService;
import com.joysistvi.univenrollmentapp.service.StudentService;

// Controller Class
// Acts as the bridge between the View and the Service layer for the Student module
public class StudentController {

    // Dependency Injection (Constructor Injection)
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

    // Get the logged-in student's profile using their linked user account ID
    public Student getStudentByUserId(int userId) {
        return studentService.getStudentByUserId(userId);
    }

    // View available (non-archived) courses
    public List<Course> listAvailableCourses() {
        return courseService.getAllCourses();
    }

    // View a student's enrollment history
    public List<Enrollment> listEnrollmentHistory(int studentId) {
        return enrollmentService.getEnrollmentHistory(studentId);
    }

    // Enroll in a course
    public String enroll(
            int studentId, int courseId, String schoolYear, Semester semester) {

        return enrollmentService.enrollStudent(
                studentId, courseId, schoolYear, semester);

    }

    // Drop an enrolled course
    public boolean drop(int enrollmentId, int studentId) {
        return enrollmentService.dropEnrollment(enrollmentId, studentId);
    }

    // View all prerequisite information
    public List<Prerequisite> listAllPrerequisites() {
        return prerequisiteService.getAllPrerequisites();
    }
}
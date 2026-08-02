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

// Controller for Student Portal
// Handles operations available to logged-in students
public class StudentPortalController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final PrerequisiteService prerequisiteService;

    public StudentPortalController(
            StudentService studentService,
            CourseService courseService,
            EnrollmentService enrollmentService,
            PrerequisiteService prerequisiteService) {

        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.prerequisiteService = prerequisiteService;
    }

    public Student getStudentByUserId(int userId) {
        return studentService.getStudentByUserId(userId);
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    public List<Enrollment> getEnrollmentHistory(int studentId) {
        return enrollmentService.getEnrollmentHistory(studentId);
    }

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

    public boolean dropEnrollment(
            int enrollmentId,
            int studentId) {

        return enrollmentService.dropEnrollment(
                enrollmentId,
                studentId);
    }

    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteService.getAllPrerequisites();
    }
}
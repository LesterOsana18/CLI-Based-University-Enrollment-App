package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.service.CourseService;

// Controller Class
// Handles requests related to course management
public class CourseController {

    // Dependency Injection
    private final CourseService courseService;

    // Constructor
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Retrieve all active courses
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Retrieve all archived courses
    public List<Course> getArchivedCourses() {
        return courseService.getArchivedCourses();
    }

    // Create a new course
    public boolean createCourse(
            String courseCode,
            String courseName,
            int units,
            int departmentId) {

        Course course = new Course(
                0,
                courseCode,
                courseName,
                units,
                departmentId);

        return courseService.createCourse(course);

    }

    // Update an existing course
    public boolean updateCourse(
            int id,
            String courseCode,
            String courseName,
            int units,
            int departmentId) {

        Course course = new Course(
                id,
                courseCode,
                courseName,
                units,
                departmentId);

        return courseService.updateCourse(id, course);

    }

    // Archive a course
    public boolean archiveCourse(int id) {
        return courseService.archiveCourse(id);
    }

    // Restore an archived course
    public boolean restoreCourse(int id) {
        return courseService.restoreCourse(id);
    }

}
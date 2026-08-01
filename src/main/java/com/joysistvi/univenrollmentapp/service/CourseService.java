package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Course;

// Service Interface
// Defines the business operations for Course management
public interface CourseService {

    // Retrieve all active courses
    List<Course> getAllCourses();

    // Retrieve archived courses
    List<Course> getArchivedCourses();

    // Create a course
    boolean createCourse(Course course);

    // Update a course
    boolean updateCourse(int id, Course course);

    // Archive a course
    boolean archiveCourse(int id);

    // Restore an archived course
    boolean restoreCourse(int id);

}
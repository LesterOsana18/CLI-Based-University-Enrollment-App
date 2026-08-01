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
    boolean save(Course course);

    // Update a course
    boolean update(int id, Course course);

    // Archive a course
    boolean archive(int id);

    // Restore an archived course
    boolean restore(int id);

    // Permanently delete a course
    boolean delete(int id);
}
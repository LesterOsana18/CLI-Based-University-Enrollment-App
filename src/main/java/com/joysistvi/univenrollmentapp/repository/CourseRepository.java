package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Course;

// Repository Interface
// Defines the database operations for Course objects
public interface CourseRepository {

    // Retrieve all active courses
    List<Course> getAllCourses();

    // Retrieve all archived courses
    List<Course> getArchivedCourses();

    // Retrieve a course by ID
    Course findById(int id);

    // Insert a new course
    boolean save(Course course);

    // Update an existing course
    boolean update(Course course);

    // Archive a course
    boolean archive(int id);

    // Restore an archived course
    boolean restore(int id);

    // Permanently delete a course
    boolean delete(int id);

}
package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.repository.CourseRepository;

// Service Implementation
// Implements the business logic for Course management
public class CourseServiceImpl implements CourseService {

    // Dependency Injection
    private final CourseRepository repository;

    // Constructor
    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Course> getAllCourses() {
        return repository.getAllCourses();
    }

    @Override
    public List<Course> getArchivedCourses() {
        return repository.getArchivedCourses();
    }

    @Override
    public boolean createCourse(Course course) {
        return repository.createCourse(course);
    }

    @Override
    public boolean updateCourse(int id, Course course) {
        return repository.updateCourse(id, course);
    }

    @Override
    public boolean archiveCourse(int id) {
        return repository.archiveCourse(id);
    }

    @Override
    public boolean restoreCourse(int id) {
        return repository.restoreCourse(id);
    }

}
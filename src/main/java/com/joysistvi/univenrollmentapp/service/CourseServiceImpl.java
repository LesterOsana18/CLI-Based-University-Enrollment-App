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
    public boolean save(Course course) {
        return repository.save(course);
    }

    @Override
    public boolean update(int id, Course course) {
        return repository.update(course);
    }

    @Override
    public boolean archive(int id) {
        return repository.archive(id);
    }

    @Override
    public boolean restore(int id) {
        return repository.restore(id);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }
}
package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.repository.CourseRepositoryImpl;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final CourseRepositoryImpl repository = new CourseRepositoryImpl();

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
    public boolean softDeleteCourse(int id) {
        return repository.softDeleteCourse(id);
    }

    @Override
    public boolean hardDeleteCourse(int id) {
        return repository.hardDeleteCourse(id);
    }
}

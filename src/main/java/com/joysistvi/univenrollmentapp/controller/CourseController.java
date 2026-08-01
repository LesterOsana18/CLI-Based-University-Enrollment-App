package com.joysistvi.univenrollmentapp.controller;

import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.service.CourseServiceImpl;
import java.util.List;

public class CourseController {
    private final CourseServiceImpl service = new CourseServiceImpl();

    public List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    public List<Course> getArchivedCourses() {
        return service.getArchivedCourses();
    }

    public boolean createCourse(String courseCode, String courseName, int units, int departmentId) {
        return service.createCourse(new Course(0, courseCode, courseName, units, departmentId));
    }

    public boolean updateCourse(int id, String courseCode, String courseName, int units, int departmentId) {
        return service.updateCourse(id, new Course(id, courseCode, courseName, units, departmentId));
    }

    public boolean deleteCourse(int id) {
        return service.softDeleteCourse(id);
    }

    public boolean permanentlyDeleteCourse(int id) {
        return service.hardDeleteCourse(id);
    }

}

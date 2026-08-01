package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Course;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    List<Course> getArchivedCourses();
    boolean createCourse(Course course);
    boolean updateCourse(int id, Course course);
    boolean softDeleteCourse(int id);
    boolean hardDeleteCourse(int id);
}

package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.model.Course;
import java.util.List;

public interface CourseRepository {
    List<Course> getAllCourses();
    List<Course> getArchivedCourses();
    boolean createCourse(Course course);
    boolean updateCourse(int id, Course course);
    boolean softDeleteCourse(int id);
    boolean hardDeleteCourse(int id);
}

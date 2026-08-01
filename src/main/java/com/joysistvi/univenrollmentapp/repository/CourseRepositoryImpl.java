package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {
    @Override
    public List<Course> getAllCourses() {
        return getCoursesByArchiveStatus(false);
    }

    @Override
    public List<Course> getArchivedCourses() {
        return getCoursesByArchiveStatus(true);
    }

    private List<Course> getCoursesByArchiveStatus(boolean archived) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.id, c.course_code, c.course_name, c.units, c.department_id, d.department_name "
                + "FROM courses c JOIN departments d ON c.department_id = d.id "
                + "WHERE c.is_archived = ? ORDER BY c.course_code";

        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setBoolean(1, archived);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getInt("units"),
                        rs.getInt("department_id"),
                        rs.getString("department_name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }

    @Override
    public boolean createCourse(Course course) {
        String query = "INSERT INTO courses (course_code, course_name, units, department_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getCourseName());
            pstmt.setInt(3, course.getUnits());
            pstmt.setInt(4, course.getDepartmentId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCourse(int id, Course course) {
        String query = "UPDATE courses SET course_code = ?, course_name = ?, units = ?, department_id = ? "
                + "WHERE id = ? AND is_archived = 0";
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getCourseName());
            pstmt.setInt(3, course.getUnits());
            pstmt.setInt(4, course.getDepartmentId());
            pstmt.setInt(5, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean softDeleteCourse(int id) {
        return executeUpdate("UPDATE courses SET is_archived = 1 WHERE id = ? AND is_archived = 0", id);
    }

    @Override
    public boolean hardDeleteCourse(int id) {
        return executeUpdate("DELETE FROM courses WHERE id = ? AND is_archived = 1", id);
    }

    private boolean executeUpdate(String query, int id) {
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

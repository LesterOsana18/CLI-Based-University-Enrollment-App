package com.joysistvi.univenrollmentapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.model.Course;

// Repository Implementation
// Implements database operations for Course objects
public class CourseRepositoryImpl implements CourseRepository {

    private final DbConnection dbConnection;

    public CourseRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Course> getAllCourses() {
        return findCourses(false);
    }

    @Override
    public List<Course> getArchivedCourses() {
        return findCourses(true);
    }

    @Override
    public Course findById(int id) {

        String sql = """
                SELECT c.*, d.department_name
                FROM courses c
                JOIN departments d
                    ON c.department_id = d.id
                WHERE c.id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("course_code"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("units"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name"));

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return null;

    }

    private List<Course> findCourses(boolean archived) {

        List<Course> courses = new ArrayList<>();

        String sql = """
                SELECT c.*, d.department_name
                FROM courses c
                JOIN departments d
                    ON c.department_id = d.id
                WHERE c.is_archived = ?
                ORDER BY c.course_code
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBoolean(1, archived);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                courses.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("course_code"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("units"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name")));

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return courses;

    }

    @Override
    public boolean save(Course course) {

        String sql = """
                INSERT INTO courses
                (course_code, course_name, units, department_id)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getCourseName());
            statement.setInt(3, course.getUnits());
            statement.setInt(4, course.getDepartmentId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    @Override
    public boolean update(Course course) {

        String sql = """
                UPDATE courses
                SET course_code = ?,
                    course_name = ?,
                    units = ?,
                    department_id = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getCourseName());
            statement.setInt(3, course.getUnits());
            statement.setInt(4, course.getDepartmentId());
            statement.setInt(5, course.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    @Override
    public boolean archive(int id) {
        return executeUpdate(
                "UPDATE courses SET is_archived = TRUE WHERE id = ?",
                id);
    }

    @Override
    public boolean restore(int id) {
        return executeUpdate(
                "UPDATE courses SET is_archived = FALSE WHERE id = ?",
                id);
    }

    @Override
    public boolean delete(int id) {
        return executeUpdate(
                "DELETE FROM courses WHERE id = ?",
                id);
    }

    private boolean executeUpdate(String sql, int id) {

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

}
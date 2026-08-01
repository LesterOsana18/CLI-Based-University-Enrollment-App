package com.joysistvi.univenrollmentapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Enrollment;

// Repository Implementation Class
// Implements all database operations for Enrollment objects
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    // Dependency Injection
    private final DbConnection dbConnection;

    // Constructor
    public EnrollmentRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Converts a MySQL enum string ('1st', '2nd', 'Summer') into our Java enum
    private Semester toSemester(String value) {

        return switch (value) {
            case "1st" -> Semester.FIRST;
            case "2nd" -> Semester.SECOND;
            case "Summer" -> Semester.SUMMER;
            default -> throw new IllegalArgumentException(
                    "Unknown semester value: " + value);
        };

    }

    // Converts our Java enum back into the exact MySQL enum string
    private String toDbValue(Semester semester) {

        return switch (semester) {
            case FIRST -> "1st";
            case SECOND -> "2nd";
            case SUMMER -> "Summer";
        };

    }

    // Helper method: maps one ResultSet row into an Enrollment object
    private Enrollment mapRow(ResultSet resultSet) throws SQLException {

        Enrollment enrollment = new Enrollment();

        enrollment.setId(resultSet.getInt("id"));
        enrollment.setStudentId(resultSet.getInt("student_id"));
        enrollment.setCourseId(resultSet.getInt("course_id"));
        enrollment.setSchoolYear(resultSet.getString("school_year"));
        enrollment.setSemester(
                toSemester(resultSet.getString("semester")));
        enrollment.setDateEnrolled(resultSet.getDate("date_enrolled"));

        return enrollment;

    }

    @Override
    public List<Enrollment> findByStudentId(int studentId) {

        List<Enrollment> enrollments = new ArrayList<>();

        String sql = "SELECT * FROM enrollments WHERE student_id = ? ORDER BY date_enrolled DESC";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                enrollments.add(mapRow(resultSet));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return enrollments;

    }

    @Override
    public Enrollment findById(int id) {

        String sql = "SELECT * FROM enrollments WHERE id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return null;

    }

    @Override
    public boolean existsByStudentCourseTerm(
            int studentId, int courseId, String schoolYear, Semester semester) {

        String sql = """
            SELECT 1 FROM enrollments
            WHERE student_id = ? AND course_id = ?
              AND school_year = ? AND semester = ?
            """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setString(3, schoolYear);
            statement.setString(4, toDbValue(semester));

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return false;

    }

    @Override
    public boolean save(Enrollment enrollment) {

        String sql = """
            INSERT INTO enrollments (student_id, course_id, school_year, semester)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, enrollment.getStudentId());
            statement.setInt(2, enrollment.getCourseId());
            statement.setString(3, enrollment.getSchoolYear());
            statement.setString(4, toDbValue(enrollment.getSemester()));

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return false;

    }

    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM enrollments WHERE id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return false;

    }
}
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

    // Converts database value to Java enum
    private Semester toSemester(String value) {

        return switch (value) {
            case "1st" -> Semester.FIRST;
            case "2nd" -> Semester.SECOND;
            case "Summer" -> Semester.SUMMER;
            default -> throw new IllegalArgumentException(
                    "Unknown semester value: " + value);
        };

    }

    // Converts Java enum to database value
    private String toDbValue(Semester semester) {

        return switch (semester) {
            case FIRST -> "1st";
            case SECOND -> "2nd";
            case SUMMER -> "Summer";
        };

    }

    // Maps a ResultSet row to an Enrollment object
    private Enrollment mapRow(ResultSet resultSet)
            throws SQLException {

        return new Enrollment(
            resultSet.getInt("id"),
            resultSet.getInt("student_id"),
            null,
            null,
            resultSet.getInt("course_id"),
            null,
            null,
            resultSet.getString("school_year"),
            toSemester(resultSet.getString("semester")),
            resultSet.getDate("date_enrolled"));

    }

    // Retrieves all enrollments from the database
    @Override
    public List<Enrollment> getAllEnrollments() {

        List<Enrollment> enrollments = new ArrayList<>();

        String sql = """
                SELECT *
                FROM enrollments
                ORDER BY date_enrolled DESC
                """;

        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                enrollments.add(mapRow(resultSet));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return enrollments;

    }

    // Retrieves all archived enrollments from the database
    @Override
    public List<Enrollment> getArchivedEnrollments() {

        List<Enrollment> enrollments = new ArrayList<>();

        String sql = """
                SELECT *
                FROM enrollments
                ORDER BY date_enrolled DESC
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                enrollments.add(mapRow(resultSet));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return enrollments;

    }

    // Searches enrollments based on a keyword
    @Override
    public List<Enrollment> searchEnrollments(String keyword) {

        List<Enrollment> enrollments = new ArrayList<>();

        String sql = """
                SELECT e.*
                FROM enrollments e
                JOIN students s
                ON e.student_id = s.id
                JOIN courses c
                ON e.course_id = c.id
                AND (
                        s.student_number LIKE ?
                    OR s.first_name LIKE ?
                    OR s.last_name LIKE ?
                    OR c.course_code LIKE ?
                    OR c.course_name LIKE ?
                    OR e.school_year LIKE ?
                    OR e.semester LIKE ?
                )
                ORDER BY e.date_enrolled DESC
                """;

        String search = "%" + keyword + "%";

        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)) {

            statement.setString(1, search);
            statement.setString(2, search);
            statement.setString(3, search);
            statement.setString(4, search);
            statement.setString(5, search);
            statement.setString(6, search);
            statement.setString(7, search);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    enrollments.add(mapRow(resultSet));
                }

            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return enrollments;

    }

    @Override
    public List<Enrollment> getEnrollmentHistory(int studentId) {

        List<Enrollment> enrollments = new ArrayList<>();

        String sql = """
                SELECT *
                FROM enrollments
                WHERE student_id = ?
                ORDER BY date_enrolled DESC
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    enrollments.add(mapRow(resultSet));
                }

            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return enrollments;

    }

    @Override
    public Enrollment getEnrollmentById(int id) {

        String sql = """
                SELECT *
                FROM enrollments
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapRow(resultSet);
                }

            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return null;

    }

    @Override
    public boolean enrollmentExists(
            int studentId,
            int courseId,
            String schoolYear,
            Semester semester) {

        String sql = """
                SELECT 1
                FROM enrollments
                WHERE student_id = ?
                  AND course_id = ?
                  AND school_year = ?
                  AND semester = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setString(3, schoolYear);
            statement.setString(4, toDbValue(semester));

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: " + e.getMessage());

        }

        return false;

    }

    @Override
    public boolean createEnrollment(Enrollment enrollment) {

        String sql = """
                INSERT INTO enrollments
                (student_id, course_id, school_year, semester)
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
    public boolean deleteEnrollment(int id) {

        String sql = """
                DELETE FROM enrollments
                WHERE id = ?
                """;

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
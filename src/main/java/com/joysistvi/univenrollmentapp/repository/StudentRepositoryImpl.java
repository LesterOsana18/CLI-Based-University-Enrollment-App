package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Repository Implementation
// Implements database operations for Student objects
public class StudentRepositoryImpl implements StudentRepository {

    // Dependency Injection
    private final DbConnection dbConnection;

    // Constructor
    public StudentRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Retrieve all active students
    @Override
    public List<Student> getAllStudents() {
        return findStudents(false);
    }

    // Retrieve all archived students
    @Override
    public List<Student> getArchivedStudents() {
        return findStudents(true);
    }

    // Retrieve student by ID
    @Override
    public Student getStudentById(int id) {

        String sql = """
                SELECT s.*,
                       d.department_name,
                       u.username
                FROM students s
                JOIN departments d
                    ON s.department_id = d.id
                LEFT JOIN users u
                    ON s.user_id = u.id
                WHERE s.id = ?
                  AND s.is_archived = FALSE
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapStudent(resultSet);
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return null;

    }

    // Retrieve student by linked user account
    @Override
    public Student getStudentByUserId(int userId) {

        String sql = """
                SELECT s.*,
                       d.department_name,
                       u.username
                FROM students s
                JOIN departments d
                    ON s.department_id = d.id
                LEFT JOIN users u
                    ON s.user_id = u.id
                WHERE s.user_id = ?
                  AND s.is_archived = FALSE
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapStudent(resultSet);
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return null;

    }

    // Search students by keyword (name, student number, or email)
    @Override
    public List<Student> searchStudents(String keyword) {

        String sql = """
                SELECT s.*,
                       d.department_name,
                       u.username
                FROM students s
                JOIN departments d
                    ON s.department_id = d.id
                LEFT JOIN users u
                    ON s.user_id = u.id
                WHERE (s.first_name LIKE ? OR
                       s.last_name LIKE ? OR
                       s.student_number LIKE ? OR
                       s.email LIKE ?)
                  AND s.is_archived = FALSE
                """;

        List<Student> students = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            statement.setString(3, searchPattern);
            statement.setString(4, searchPattern);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                students.add(mapStudent(resultSet));
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return students;

    }

    // Helper method for retrieving students
    private List<Student> findStudents(boolean archived) {

        List<Student> students = new ArrayList<>();

        String sql = """
                SELECT s.*,
                       d.department_name,
                       u.username
                FROM students s
                JOIN departments d
                    ON s.department_id = d.id
                LEFT JOIN users u
                    ON s.user_id = u.id
                WHERE s.is_archived = ?
                ORDER BY s.student_number
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBoolean(1, archived);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                students.add(mapStudent(resultSet));

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return students;

    }

    // Maps one database record into a Student object
    private Student mapStudent(ResultSet resultSet)
            throws SQLException {

        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("student_number"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getInt("department_id"),
                resultSet.getString("department_name"),
                resultSet.getInt("user_id"),
                resultSet.getString("username"),
                Status.valueOf(resultSet.getString("status")));

    }

    // Helper method for archive, restore, and delete operations
    private boolean executeUpdate(
            String sql,
            int id) {

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

    // Create a new student
    @Override
    public boolean createStudent(Student student) {

        String sql = """
                INSERT INTO students(
                    student_number,
                    first_name,
                    last_name,
                    email,
                    department_id,
                    status
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getStudentNumber());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setInt(5, student.getDepartmentId());
            statement.setString(6, student.getStatus().name());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Update an existing student
    @Override
    public boolean updateStudent(Student student) {

        String sql = """
                UPDATE students
                SET student_number = ?,
                    first_name = ?,
                    last_name = ?,
                    email = ?,
                    department_id = ?,
                    status = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    student.getStudentNumber());

            statement.setString(
                    2,
                    student.getFirstName());

            statement.setString(
                    3,
                    student.getLastName());

            statement.setString(
                    4,
                    student.getEmail());

            statement.setInt(
                    5,
                    student.getDepartmentId());

            statement.setString(
                    6,
                    student.getStatus().name());

            statement.setInt(
                    7,
                    student.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: "
                            + e.getMessage());

        }

        return false;

    }

    // Archive a student
    @Override
    public boolean archiveStudent(int id) {

        return executeUpdate(
                """
                UPDATE students
                SET is_archived = TRUE
                WHERE id = ?
                """,
                id);

    }

    // Restore an archived student
    @Override
    public boolean restoreStudent(int id) {

        return executeUpdate(
                """
                UPDATE students
                SET is_archived = FALSE
                WHERE id = ?
                """,
                id);

    }

    // Permanently delete a student
    @Override
    public boolean deleteStudent(int id) {

        return executeUpdate(
                """
                DELETE FROM students
                WHERE id = ?
                """,
                id);

    }

    // Reset a student's account password
    @Override
    public boolean resetPassword(
            int userId,
            String newPassword) {

        String sql = """
                UPDATE users
                SET password = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    com.joysistvi.univenrollmentapp.utils.PasswordUtils
                            .hashPassword(newPassword));

            statement.setInt(
                    2,
                    userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Database Error: "
                            + e.getMessage());

        }

        return false;

    }

}
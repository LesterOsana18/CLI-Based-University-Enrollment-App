package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {
    private static final String SELECT_ENROLLMENTS = "SELECT e.id, e.student_id, s.student_number, "
            + "CONCAT(s.first_name, ' ', s.last_name) AS student_name, e.course_id, "
            + "c.course_code, c.course_name, e.school_year, e.semester, e.date_enrolled "
            + "FROM enrollments e JOIN students s ON e.student_id = s.id "
            + "JOIN courses c ON e.course_id = c.id ";

    @Override
    public List<Enrollment> getAllEnrollments() {
        return executeEnrollmentQuery(SELECT_ENROLLMENTS
                + "ORDER BY e.school_year DESC, e.semester, s.student_number, c.course_code", null);
    }

    @Override
    public List<Enrollment> searchEnrollments(String keyword) {
        String query = SELECT_ENROLLMENTS
                + "WHERE LOWER(s.student_number) LIKE ? OR LOWER(s.first_name) LIKE ? "
                + "OR LOWER(s.last_name) LIKE ? OR LOWER(c.course_code) LIKE ? "
                + "OR LOWER(c.course_name) LIKE ? OR LOWER(e.school_year) LIKE ? "
                + "OR LOWER(e.semester) LIKE ? "
                + "ORDER BY e.school_year DESC, e.semester, s.student_number, c.course_code";
        return executeEnrollmentQuery(query, keyword);
    }

    private List<Enrollment> executeEnrollmentQuery(String query, String keyword) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            if (keyword != null) {
                String searchTerm = "%" + keyword.toLowerCase() + "%";
                for (int index = 1; index <= 7; index++) pstmt.setString(index, searchTerm);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) enrollments.add(mapEnrollment(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return enrollments;
    }

    private Enrollment mapEnrollment(ResultSet rs) throws SQLException {
        return new Enrollment(
                rs.getInt("id"), rs.getInt("student_id"), rs.getString("student_number"),
                rs.getString("student_name"), rs.getInt("course_id"),
                rs.getString("course_code"), rs.getString("course_name"),
                rs.getString("school_year"), mapSemester(rs.getString("semester")),
                rs.getDate("date_enrolled"));
    }

    private Semester mapSemester(String semester) {
        return switch (semester) {
            case "1st" -> Semester.FIRST;
            case "2nd" -> Semester.SECOND;
            case "Summer" -> Semester.SUMMER;
            default -> throw new IllegalArgumentException("Unknown semester value: " + semester);
        };
    }
}

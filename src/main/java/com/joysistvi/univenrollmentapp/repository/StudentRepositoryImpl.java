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

public class StudentRepositoryImpl implements StudentRepository {
    private static final String SELECT_STUDENTS = "SELECT s.id, s.student_number, s.first_name, s.last_name, "
            + "s.email, s.department_id, s.status, d.department_name "
            + "FROM students s JOIN departments d ON s.department_id = d.id ";

    @Override
    public List<Student> getAllStudents() {
        return executeStudentQuery(SELECT_STUDENTS + "ORDER BY s.student_number", null);
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        String query = SELECT_STUDENTS
                + "WHERE LOWER(s.student_number) LIKE ? OR LOWER(s.first_name) LIKE ? "
                + "OR LOWER(s.last_name) LIKE ? OR LOWER(s.email) LIKE ? "
                + "OR LOWER(d.department_name) LIKE ? ORDER BY s.student_number";
        return executeStudentQuery(query, keyword);
    }

    private List<Student> executeStudentQuery(String query, String keyword) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            if (keyword != null) {
                String searchTerm = "%" + keyword.toLowerCase() + "%";
                for (int index = 1; index <= 5; index++) pstmt.setString(index, searchTerm);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) students.add(mapStudent(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    private Student mapStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"), rs.getString("student_number"),
                rs.getString("first_name"), rs.getString("last_name"),
                rs.getString("email"), rs.getInt("department_id"),
                rs.getString("department_name"), Status.valueOf(rs.getString("status")));
    }
}

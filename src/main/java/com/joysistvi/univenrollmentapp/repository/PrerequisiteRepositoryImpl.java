package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrerequisiteRepositoryImpl implements PrerequisiteRepository {
    @Override
    public List<Prerequisite> getAllPrerequisites() {
        List<Prerequisite> prerequisites = new ArrayList<>();
        String query = "SELECT p.id, p.course_id, c.course_code, c.course_name, "
                + "p.prerequisite_course_id, pc.course_code AS prerequisite_course_code, "
                + "pc.course_name AS prerequisite_course_name "
                + "FROM prerequisites p "
                + "JOIN courses c ON p.course_id = c.id "
                + "JOIN courses pc ON p.prerequisite_course_id = pc.id "
                + "ORDER BY c.course_code, pc.course_code";

        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                prerequisites.add(new Prerequisite(
                        rs.getInt("id"),
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getInt("prerequisite_course_id"),
                        rs.getString("prerequisite_course_code"),
                        rs.getString("prerequisite_course_name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return prerequisites;
    }

    @Override
    public boolean createPrerequisite(Prerequisite prerequisite) {
        String query = "INSERT INTO prerequisites (course_id, prerequisite_course_id) VALUES (?, ?)";
        return executeWrite(query, prerequisite, null);
    }

    @Override
    public boolean updatePrerequisite(int id, Prerequisite prerequisite) {
        String query = "UPDATE prerequisites SET course_id = ?, prerequisite_course_id = ? WHERE id = ?";
        return executeWrite(query, prerequisite, id);
    }

    @Override
    public boolean deletePrerequisite(int id) {
        String query = "DELETE FROM prerequisites WHERE id = ?";
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean executeWrite(String query, Prerequisite prerequisite, Integer id) {
        try (Connection conn = new DbConnection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, prerequisite.getCourseId());
            pstmt.setInt(2, prerequisite.getPrerequisiteCourseId());
            if (id != null) pstmt.setInt(3, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

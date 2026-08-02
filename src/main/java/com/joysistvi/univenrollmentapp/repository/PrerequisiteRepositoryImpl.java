package com.joysistvi.univenrollmentapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.model.Prerequisite;

// Repository Implementation Class
// Implements all database operations for Prerequisite objects
public class PrerequisiteRepositoryImpl implements PrerequisiteRepository {

    // Dependency Injection
    private final DbConnection dbConnection;

    // Constructor
    public PrerequisiteRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Retrieve all prerequisites
    @Override
    public List<Prerequisite> getAllPrerequisites() {

        List<Prerequisite> prerequisites = new ArrayList<>();

        String sql = """
                SELECT p.id,
                       p.course_id,
                       c.course_code,
                       c.course_name,
                       p.prerequisite_course_id,
                       pc.course_code AS prerequisite_course_code,
                       pc.course_name AS prerequisite_course_name
                FROM prerequisites p
                JOIN courses c
                  ON p.course_id = c.id
                JOIN courses pc
                  ON p.prerequisite_course_id = pc.id
                ORDER BY c.course_code, pc.course_code
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                prerequisites.add(new Prerequisite(
                        resultSet.getInt("id"),
                        resultSet.getInt("course_id"),
                        resultSet.getString("course_code"),
                        resultSet.getString("course_name"),
                        resultSet.getInt("prerequisite_course_id"),
                        resultSet.getString("prerequisite_course_code"),
                        resultSet.getString("prerequisite_course_name")));

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return prerequisites;

    }

    // Retrieve a prerequisite by ID
    @Override
    public Prerequisite getPrerequisiteById(int id) {

        String sql = """
                SELECT p.id,
                       p.course_id,
                       c.course_code,
                       c.course_name,
                       p.prerequisite_course_id,
                       pc.course_code AS prerequisite_course_code,
                       pc.course_name AS prerequisite_course_name
                FROM prerequisites p
                JOIN courses c
                  ON p.course_id = c.id
                JOIN courses pc
                  ON p.prerequisite_course_id = pc.id
                WHERE p.id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Prerequisite(
                            resultSet.getInt("id"),
                            resultSet.getInt("course_id"),
                            resultSet.getString("course_code"),
                            resultSet.getString("course_name"),
                            resultSet.getInt("prerequisite_course_id"),
                            resultSet.getString("prerequisite_course_code"),
                            resultSet.getString("prerequisite_course_name"));

                }

            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return null;

    }

    // Create a prerequisite
    @Override
    public boolean createPrerequisite(Prerequisite prerequisite) {

        String sql = """
                INSERT INTO prerequisites (course_id, prerequisite_course_id)
                VALUES (?, ?)
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, prerequisite.getCourseId());
            statement.setInt(2, prerequisite.getPrerequisiteCourseId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Update a prerequisite
    @Override
    public boolean updatePrerequisite(
            int id,
            int courseId,
            int prerequisiteCourseId) {

        String sql = """
                UPDATE prerequisites
                SET course_id = ?,
                    prerequisite_course_id = ?
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, courseId);
            statement.setInt(2, prerequisiteCourseId);
            statement.setInt(3, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Delete a prerequisite
    @Override
    public boolean deletePrerequisite(int id) {

        String sql = """
                DELETE FROM prerequisites
                WHERE id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

    // Check if a prerequisite relationship already exists
    @Override
    public boolean relationshipExists(
            int courseId,
            int prerequisiteCourseId) {

        String sql = """
                SELECT 1
                FROM prerequisites
                WHERE course_id = ?
                  AND prerequisite_course_id = ?
                """;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, courseId);
            statement.setInt(2, prerequisiteCourseId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

        return false;

    }

}
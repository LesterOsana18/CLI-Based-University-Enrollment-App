package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Prerequisite;

public interface PrerequisiteRepository {

    // Retrieves all prerequisites from the database
    List<Prerequisite> getAllPrerequisites();

    // Finds a prerequisite by its ID
    Prerequisite getPrerequisiteById(int id);

    // Creates a new prerequisite in the database
    boolean createPrerequisite(Prerequisite prerequisite);

    // Updates an existing prerequisite in the database
    boolean updatePrerequisite(int id, int courseId, int prerequisiteCourseId);

    // Permanently deletes a prerequisite from the database
    boolean deletePrerequisite(int id);

    // Checks if a prerequisite relationship exists between two courses
    boolean relationshipExists(int courseId, int prerequisiteCourseId);
}
package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Prerequisite;

// Service Interface
// Defines the business operations for Prerequisite management
public interface PrerequisiteService {

    // Retrieves all prerequisites
    List<Prerequisite> getAllPrerequisites();

    // Retrieves a prerequisite by its ID
    Prerequisite getPrerequisiteById(int id);

    // Creates a new prerequisite relationship
    boolean createPrerequisite(
            int courseId,
            int prerequisiteCourseId);

    // Updates an existing prerequisite relationship
    boolean updatePrerequisite(
            int id,
            int courseId,
            int prerequisiteCourseId);
    
    // Deletes a prerequisite relationship
    boolean deletePrerequisite(int id);

    // Checks if a prerequisite relationship exists between two courses
    boolean relationshipExists(int courseId, int prerequisiteCourseId);
}
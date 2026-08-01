package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.service.PrerequisiteService;

// Controller Class
// Handles requests related to course prerequisites
public class PrerequisiteController {

    // Dependency Injection
    private final PrerequisiteService prerequisiteService;

    // Constructor
    public PrerequisiteController(
            PrerequisiteService prerequisiteService) {

        this.prerequisiteService = prerequisiteService;

    }

    // Retrieve all prerequisites
    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteService.getAllPrerequisites();
    }

    // Create a prerequisite
    public boolean createPrerequisite(
            int courseId,
            int prerequisiteCourseId) {

        return prerequisiteService.createPrerequisite(
                courseId,
                prerequisiteCourseId);

    }

    // Update a prerequisite
    public boolean updatePrerequisite(
            int id,
            int courseId,
            int prerequisiteCourseId) {

        return prerequisiteService.updatePrerequisite(
                id,
                courseId,
                prerequisiteCourseId);

    }

    // Remove a prerequisite
    public boolean deletePrerequisite(int id) {
        return prerequisiteService.deletePrerequisite(id);
    }

}
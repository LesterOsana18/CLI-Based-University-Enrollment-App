package com.joysistvi.univenrollmentapp.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.repository.PrerequisiteRepository;

// Service Implementation
// Implements the business operations for Prerequisite objects
public class PrerequisiteServiceImpl implements PrerequisiteService {

    // Dependency Injection
    private final PrerequisiteRepository prerequisiteRepository;

    // Constructor
    public PrerequisiteServiceImpl(
            PrerequisiteRepository prerequisiteRepository) {

        this.prerequisiteRepository = prerequisiteRepository;

    }

    // Retrieve all prerequisites
    @Override
    public List<Prerequisite> getAllPrerequisites() {
        return prerequisiteRepository.getAllPrerequisites();
    }

    // Retrieve a prerequisite by ID
    @Override
    public Prerequisite getPrerequisiteById(int id) {
        return prerequisiteRepository.getPrerequisiteById(id);
    }

    // Create a prerequisite relationship
    @Override
    public boolean createPrerequisite(
            int courseId,
            int prerequisiteCourseId) {

        if (!isValidRelationship(courseId, prerequisiteCourseId, null)) {
            return false;
        }

        return prerequisiteRepository.createPrerequisite(
                new Prerequisite(courseId, prerequisiteCourseId));

    }

    // Update a prerequisite relationship
    @Override
    public boolean updatePrerequisite(
            int id,
            int courseId,
            int prerequisiteCourseId) {

        if (!isValidRelationship(courseId, prerequisiteCourseId, id)) {
            return false;
        }

        return prerequisiteRepository.updatePrerequisite(
                id,
                courseId,
                prerequisiteCourseId);

    }

    // Delete a prerequisite relationship
    @Override
    public boolean deletePrerequisite(int id) {
        return prerequisiteRepository.deletePrerequisite(id);
    }

    // Check if a prerequisite relationship already exists
    @Override
    public boolean relationshipExists(
            int courseId,
            int prerequisiteCourseId) {

        return prerequisiteRepository.relationshipExists(
                courseId,
                prerequisiteCourseId);

    }

    // ==========================================================
    // Helper Methods
    // ==========================================================

    // Validates that the prerequisite relationship does not create a cycle
    private boolean isValidRelationship(
            int courseId,
            int prerequisiteCourseId,
            Integer ignoredPrerequisiteId) {

        if (courseId == prerequisiteCourseId) {
            return false;
        }

        Map<Integer, Set<Integer>> prerequisitesByCourse = new HashMap<>();

        for (Prerequisite prerequisite : prerequisiteRepository.getAllPrerequisites()) {

            if (ignoredPrerequisiteId != null
                    && prerequisite.getId() == ignoredPrerequisiteId) {
                continue;
            }

            prerequisitesByCourse
                    .computeIfAbsent(
                            prerequisite.getCourseId(),
                            key -> new HashSet<>())
                    .add(prerequisite.getPrerequisiteCourseId());

        }

        prerequisitesByCourse
                .computeIfAbsent(courseId, key -> new HashSet<>())
                .add(prerequisiteCourseId);

        return !hasPath(
                prerequisiteCourseId,
                courseId,
                prerequisitesByCourse,
                new HashSet<>());

    }

    // Detects circular prerequisite chains
    private boolean hasPath(
            int currentCourseId,
            int targetCourseId,
            Map<Integer, Set<Integer>> prerequisitesByCourse,
            Set<Integer> visited) {

        if (currentCourseId == targetCourseId) {
            return true;
        }

        if (!visited.add(currentCourseId)) {
            return false;
        }

        for (int prerequisiteId
                : prerequisitesByCourse.getOrDefault(currentCourseId, Set.of())) {

            if (hasPath(
                    prerequisiteId,
                    targetCourseId,
                    prerequisitesByCourse,
                    visited)) {

                return true;

            }
        }

        return false;

    }

}
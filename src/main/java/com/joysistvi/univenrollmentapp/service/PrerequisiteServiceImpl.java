package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.repository.PrerequisiteRepository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Service Implementation
// Implements the business operations for Prerequisite objects
public class PrerequisiteServiceImpl implements PrerequisiteService {
    
    // Dependency Injection
    private final PrerequisiteRepository repository;

    // Constructor
    public PrerequisiteServiceImpl(
            PrerequisiteRepository repository) {

        this.repository = repository;

    }

    // Method to retrieve all prerequisites
    @Override
    public List<Prerequisite> getAllPrerequisites() {
        return repository.getAllPrerequisites();
    }

    // Method to create a new prerequisite relationship
    @Override
    public boolean createPrerequisite(int courseId, int prerequisiteCourseId) {
        if (!isValidRelationship(courseId, prerequisiteCourseId, null)) return false;
        return repository.createPrerequisite(new Prerequisite(0, courseId, prerequisiteCourseId));
    }

    // Method to update an existing prerequisite relationship
    @Override
    public boolean updatePrerequisite(int id, int courseId, int prerequisiteCourseId) {
        if (!isValidRelationship(courseId, prerequisiteCourseId, id)) return false;
        return repository.updatePrerequisite(id, new Prerequisite(id, courseId, prerequisiteCourseId));
    }

    // Method to delete a prerequisite relationship
    @Override
    public boolean deletePrerequisite(int id) {
        return repository.deletePrerequisite(id);
    }

    // Helper method to validate the prerequisite relationship
    private boolean isValidRelationship(int courseId, int prerequisiteCourseId, Integer ignoredPrerequisiteId) {
        if (courseId == prerequisiteCourseId) return false;

        Map<Integer, Set<Integer>> prerequisitesByCourse = new HashMap<>();
        for (Prerequisite prerequisite : repository.getAllPrerequisites()) {
            if (ignoredPrerequisiteId != null && prerequisite.getId() == ignoredPrerequisiteId) continue;
            prerequisitesByCourse
                    .computeIfAbsent(prerequisite.getCourseId(), key -> new HashSet<>())
                    .add(prerequisite.getPrerequisiteCourseId());
        }
        prerequisitesByCourse.computeIfAbsent(courseId, key -> new HashSet<>()).add(prerequisiteCourseId);
        return !hasPath(prerequisiteCourseId, courseId, prerequisitesByCourse, new HashSet<>());
    }

    // Recursive method to check for cycles in the prerequisite graph
    private boolean hasPath(int currentCourseId, int targetCourseId,
            Map<Integer, Set<Integer>> prerequisitesByCourse, Set<Integer> visited) {
        if (currentCourseId == targetCourseId) return true;
        if (!visited.add(currentCourseId)) return false;
        for (int prerequisiteId : prerequisitesByCourse.getOrDefault(currentCourseId, Set.of())) {
            if (hasPath(prerequisiteId, targetCourseId, prerequisitesByCourse, visited)) return true;
        }
        return false;
    }
}

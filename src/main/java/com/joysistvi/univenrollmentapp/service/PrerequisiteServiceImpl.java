package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.repository.PrerequisiteRepositoryImpl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrerequisiteServiceImpl implements PrerequisiteService {
    private final PrerequisiteRepositoryImpl repository = new PrerequisiteRepositoryImpl();

    @Override
    public List<Prerequisite> getAllPrerequisites() {
        return repository.getAllPrerequisites();
    }

    @Override
    public boolean createPrerequisite(int courseId, int prerequisiteCourseId) {
        if (!isValidRelationship(courseId, prerequisiteCourseId, null)) return false;
        return repository.createPrerequisite(new Prerequisite(0, courseId, prerequisiteCourseId));
    }

    @Override
    public boolean updatePrerequisite(int id, int courseId, int prerequisiteCourseId) {
        if (!isValidRelationship(courseId, prerequisiteCourseId, id)) return false;
        return repository.updatePrerequisite(id, new Prerequisite(id, courseId, prerequisiteCourseId));
    }

    @Override
    public boolean deletePrerequisite(int id) {
        return repository.deletePrerequisite(id);
    }

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

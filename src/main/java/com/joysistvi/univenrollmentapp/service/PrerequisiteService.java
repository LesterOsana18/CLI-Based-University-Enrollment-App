package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Prerequisite;

// Service Interface
// Defines the business operations for Prerequisite management
public interface PrerequisiteService {

    List<Prerequisite> getAllPrerequisites();

    boolean createPrerequisite(
            int courseId,
            int prerequisiteCourseId);

    boolean updatePrerequisite(
            int id,
            int courseId,
            int prerequisiteCourseId);

    boolean deletePrerequisite(int id);

}
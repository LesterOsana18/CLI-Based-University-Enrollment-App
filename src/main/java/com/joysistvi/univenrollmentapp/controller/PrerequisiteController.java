package com.joysistvi.univenrollmentapp.controller;

import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.service.PrerequisiteServiceImpl;
import java.util.List;

public class PrerequisiteController {
    private final PrerequisiteServiceImpl service = new PrerequisiteServiceImpl();

    public List<Prerequisite> getAllPrerequisites() {
        return service.getAllPrerequisites();
    }

    public boolean createPrerequisite(int courseId, int prerequisiteCourseId) {
        return service.createPrerequisite(courseId, prerequisiteCourseId);
    }

    public boolean updatePrerequisite(int id, int courseId, int prerequisiteCourseId) {
        return service.updatePrerequisite(id, courseId, prerequisiteCourseId);
    }

    public boolean deletePrerequisite(int id) {
        return service.deletePrerequisite(id);
    }

}

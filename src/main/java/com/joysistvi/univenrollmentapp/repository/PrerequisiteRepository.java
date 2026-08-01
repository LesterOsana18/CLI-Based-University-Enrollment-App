package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Prerequisite;

public interface PrerequisiteRepository {

    List<Prerequisite> getAllPrerequisites();

    Prerequisite findById(int id);

    boolean save(Prerequisite prerequisite);

    boolean update(int id, int courseId, int prerequisiteCourseId);

    boolean delete(int id);

}
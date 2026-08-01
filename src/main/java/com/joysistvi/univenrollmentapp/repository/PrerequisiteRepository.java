package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.model.Prerequisite;
import java.util.List;

public interface PrerequisiteRepository {
    List<Prerequisite> getAllPrerequisites();
    boolean createPrerequisite(Prerequisite prerequisite);
    boolean updatePrerequisite(int id, Prerequisite prerequisite);
    boolean deletePrerequisite(int id);
}

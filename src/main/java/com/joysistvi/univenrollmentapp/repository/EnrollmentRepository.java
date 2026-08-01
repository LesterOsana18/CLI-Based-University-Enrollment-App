package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.model.Enrollment;
import java.util.List;

public interface EnrollmentRepository {
    List<Enrollment> getAllEnrollments();
    List<Enrollment> searchEnrollments(String keyword);
}

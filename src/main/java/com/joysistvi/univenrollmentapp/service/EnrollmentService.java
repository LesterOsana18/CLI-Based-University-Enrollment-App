package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Enrollment;
import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    List<Enrollment> searchEnrollments(String keyword);
}

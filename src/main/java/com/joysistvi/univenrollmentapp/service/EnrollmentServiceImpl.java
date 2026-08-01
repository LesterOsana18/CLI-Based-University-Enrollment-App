package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.repository.EnrollmentRepositoryImpl;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepositoryImpl repository = new EnrollmentRepositoryImpl();

    @Override
    public List<Enrollment> getAllEnrollments() {
        return repository.getAllEnrollments();
    }

    @Override
    public List<Enrollment> searchEnrollments(String keyword) {
        return repository.searchEnrollments(keyword.trim());
    }
}

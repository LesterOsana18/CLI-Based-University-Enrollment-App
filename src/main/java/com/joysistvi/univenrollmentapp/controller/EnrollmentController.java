package com.joysistvi.univenrollmentapp.controller;

import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.service.EnrollmentServiceImpl;
import java.util.List;

public class EnrollmentController {
    private final EnrollmentServiceImpl service = new EnrollmentServiceImpl();

    public List<Enrollment> getAllEnrollments() {
        return service.getAllEnrollments();
    }

    public List<Enrollment> searchEnrollments(String keyword) {
        return service.searchEnrollments(keyword);
    }

}

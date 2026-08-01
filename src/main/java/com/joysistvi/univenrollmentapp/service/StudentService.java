package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Student;

// Service Interface
// Defines the business operations for Student management
public interface StudentService {

    // Retrieve a student using the linked user account
    Student getStudentByUserId(int userId);

}
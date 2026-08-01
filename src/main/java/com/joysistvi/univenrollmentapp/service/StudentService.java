package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Student;
//import java.util.List;

//import com.joysistvi.univenrollmentapp.model.Student;

// Service Interface
// Defines the business operations for Student objects
public interface StudentService {

    // Retrieve a student's profile using their linked user account ID
    Student getStudentByUserId(int userId);
}
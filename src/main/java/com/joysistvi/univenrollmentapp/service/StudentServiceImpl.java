package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.repository.StudentRepository;

// Service Implementation
// Implements the business operations for Student management
public class StudentServiceImpl implements StudentService {

    // Dependency Injection
    private final StudentRepository studentRepository;

    // Constructor
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Method to retrieve a student using the linked user account
    @Override
    public Student getStudentByUserId(int userId) {
        return studentRepository.findByUserId(userId);
    }

}
package com.joysistvi.univenrollmentapp.repository;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Student;

// Repository Interface
// Defines the database operations for Student objects
public interface StudentRepository {

    // Retrieve all students
    List<Student> findAll();

    // Retrieve a student by ID
    Student findById(int id);

    // Retrieve a student by their linked user account ID
    Student findByUserId(int userId);

    // Insert a new student
    boolean save(Student student);

    // Update an existing student
    boolean update(Student student);

    // Delete a student
    boolean delete(int id);

    // Check if a student number already exists
    boolean studentNumberExists(String studentNumber);
}
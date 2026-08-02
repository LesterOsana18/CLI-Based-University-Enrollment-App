package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Student;

// Service Interface
// Defines the business operations for Student management
public interface StudentService {

    // Retrieve all students
    List<Student> getAllStudents();

    // Retrieve all archived students
    List<Student> getArchivedStudents();

    // Retrieve a student by ID
    Student getStudentById(int id);

    // Retrieve a student using the linked user account
    Student getStudentByUserId(int userId);

    // Create a new student
    boolean createStudent(Student student, String password);

    // Update an existing student
    boolean updateStudent(Student student);

    // Archive a student
    boolean archiveStudent(int id);

    // Restore an archived student
    boolean restoreStudent(int id);

    // Permanently delete a student
    boolean deleteStudent(int id);

    // Reset the student's account password
    boolean resetPassword(int userId, String newPassword);

}
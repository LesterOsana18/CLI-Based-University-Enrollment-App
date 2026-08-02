package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.model.Student;
import java.util.List;

// Repository Interface
// Defines the database operations for Student objects
public interface StudentRepository {

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
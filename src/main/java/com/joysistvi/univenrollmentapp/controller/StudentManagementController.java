package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.service.StudentService;

// Controller for Student Management
// Handles CRUD operations for Administrators and Registrars
public class StudentManagementController {

    // Dependency Injection
    private final StudentService studentService;

    // Constructor
    public StudentManagementController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Retrieve all archived students
    public List<Student> getArchivedStudents() {
        return studentService.getArchivedStudents();
    }

    // Retrieve a student by ID
    public Student getStudentById(int id) {
        return studentService.getStudentById(id);
    }

    // Search students by keyword (name, student number, or email)
    public List<Student> searchStudents(String keyword) {
        return studentService.searchStudents(keyword);
    }

    // Create a new student with an associated user account
    public boolean createStudent(Student student, String password) {
        return studentService.createStudent(student, password);
    }

    // Update an existing student
    public boolean updateStudent(Student student) {
        return studentService.updateStudent(student);
    }

    // Archive a student
    public boolean archiveStudent(int id) {
        return studentService.archiveStudent(id);
    }

    // Restore an archived student
    public boolean restoreStudent(int id) {
        return studentService.restoreStudent(id);
    }

    // Permanently delete a student
    public boolean deleteStudent(int id) {
        return studentService.deleteStudent(id);
    }

    // Reset a student's password
    public boolean resetPassword(int userId, String newPassword) {
        return studentService.resetPassword(userId, newPassword);
    }
}
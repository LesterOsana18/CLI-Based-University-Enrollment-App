package com.joysistvi.univenrollmentapp.controller;

import java.util.List;

import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.service.StudentService;

// Controller Class
// Acts as the bridge between the View and the Service layer
// for the Student module
public class StudentController {

    // Dependency Injection
    private final StudentService studentService;

    // Constructor
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Retrieve archived students
    public List<Student> getArchivedStudents() {
        return studentService.getArchivedStudents();
    }

    // Retrieve a student by ID
    public Student getStudentById(int id) {
        return studentService.getStudentById(id);
    }

    // Retrieve a student by user ID
    public Student getStudentByUserId(int userId) {
        return studentService.getStudentByUserId(userId);
    }

    // Create a new student
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

    // Reset the password of a student
    public boolean resetPassword(int userId, String newPassword) {
        return studentService.resetPassword(userId, newPassword);
    }

}
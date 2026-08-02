package com.joysistvi.univenrollmentapp.service;

import java.util.List;

import com.joysistvi.univenrollmentapp.repository.StudentRepository;
import com.joysistvi.univenrollmentapp.model.Student;

// Service Implementation
// Implements the business operations for Student management
public class StudentServiceImpl implements StudentService {

    // Dependency Injection
    private final StudentRepository studentRepository;

    // Constructor
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Retrieve all students
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    // Retrieve all archived students
    @Override
    public List<Student> getArchivedStudents() {
        return studentRepository.getArchivedStudents();
    }

    // Retrieve a student by ID
    @Override
    public Student getStudentById(int id) {
        return studentRepository.getStudentById(id);
    }

    // Retrieve a student using the linked user account
    @Override
    public Student getStudentByUserId(int userId) {
        return studentRepository.getStudentByUserId(userId);
    }

    // Create a new student
    @Override
    public boolean createStudent(Student student, String password) {
        return studentRepository.createStudent(student, password);
    }

    // Update an existing student
    @Override
    public boolean updateStudent(Student student) {
        return studentRepository.updateStudent(student);
    }

    // Archive a student
    @Override
    public boolean archiveStudent(int id) {
        return studentRepository.archiveStudent(id);
    }

    // Restore an archived student
    @Override
    public boolean restoreStudent(int id) {
        return studentRepository.restoreStudent(id);
    }

    // Permanently delete a student
    @Override
    public boolean deleteStudent(int id) {
        return studentRepository.deleteStudent(id);
    }

    // Reset the student's account password
    @Override
    public boolean resetPassword(int userId, String newPassword) {
        return studentRepository.resetPassword(userId, newPassword);
    }

}
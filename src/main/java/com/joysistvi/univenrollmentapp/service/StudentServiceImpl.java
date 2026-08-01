package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.repository.StudentRepositoryImpl;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepositoryImpl repository = new StudentRepositoryImpl();

    @Override
    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        return repository.searchStudents(keyword.trim());
    }
}

package com.joysistvi.univenrollmentapp.controller;

import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.service.StudentServiceImpl;
import java.util.List;

public class StudentController {
    private final StudentServiceImpl service = new StudentServiceImpl();

    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    public List<Student> searchStudents(String keyword) {
        return service.searchStudents(keyword);
    }

}

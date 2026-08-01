package com.joysistvi.univenrollmentapp.service;

import com.joysistvi.univenrollmentapp.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    List<Student> searchStudents(String keyword);
}

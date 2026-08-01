package com.joysistvi.univenrollmentapp.repository;

import com.joysistvi.univenrollmentapp.model.Student;
import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();
    List<Student> searchStudents(String keyword);
}

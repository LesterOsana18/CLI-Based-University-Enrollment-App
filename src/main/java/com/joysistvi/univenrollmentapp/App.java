package com.joysistvi.univenrollmentapp;

import java.util.Scanner;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.controller.StudentController;
import com.joysistvi.univenrollmentapp.controller.UserController;
import com.joysistvi.univenrollmentapp.repository.EnrollmentRepository;
import com.joysistvi.univenrollmentapp.repository.EnrollmentRepositoryImpl;
import com.joysistvi.univenrollmentapp.repository.StudentRepository;
import com.joysistvi.univenrollmentapp.repository.StudentRepositoryImpl;
import com.joysistvi.univenrollmentapp.repository.UserRepository;
import com.joysistvi.univenrollmentapp.repository.UserRepositoryImpl;
import com.joysistvi.univenrollmentapp.service.CourseService;
import com.joysistvi.univenrollmentapp.service.CourseServiceImpl;
import com.joysistvi.univenrollmentapp.service.EnrollmentService;
import com.joysistvi.univenrollmentapp.service.EnrollmentServiceImpl;
import com.joysistvi.univenrollmentapp.service.PrerequisiteService;
import com.joysistvi.univenrollmentapp.service.PrerequisiteServiceImpl;
import com.joysistvi.univenrollmentapp.service.StudentService;
import com.joysistvi.univenrollmentapp.service.StudentServiceImpl;
import com.joysistvi.univenrollmentapp.service.UserService;
import com.joysistvi.univenrollmentapp.service.UserServiceImpl;
import com.joysistvi.univenrollmentapp.view.LoginView;
import com.joysistvi.univenrollmentapp.view.MainMenuView;

// Main Class
// Entry point of the University Enrollment Application
public class App {

    // Shared Scanner instance
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // ==========================================================
        // DATABASE CONNECTION
        // ==========================================================

        DbConnection dbConnection = new DbConnection();

        // ==========================================================
        // DEPENDENCY INJECTION - USER MODULE
        // ==========================================================

        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserService userService = new UserServiceImpl(userRepository);
        UserController userController = new UserController(userService);

        // ==========================================================
        // DEPENDENCY INJECTION - STUDENT MODULE
        // ==========================================================

        StudentRepository studentRepository = new StudentRepositoryImpl(dbConnection);
        EnrollmentRepository enrollmentRepository = new EnrollmentRepositoryImpl(dbConnection);

        StudentService studentService = new StudentServiceImpl(studentRepository);
        CourseService courseService = new CourseServiceImpl();
        PrerequisiteService prerequisiteService = new PrerequisiteServiceImpl();
        EnrollmentService enrollmentService =
                new EnrollmentServiceImpl(enrollmentRepository, studentRepository);

        StudentController studentController = new StudentController(
                studentService, courseService, enrollmentService, prerequisiteService);

        // ==========================================================
        // VIEWS
        // ==========================================================

        LoginView loginView = new LoginView(userController, input);
        MainMenuView mainMenuView = new MainMenuView(input, studentController);

        // ==========================================================
        // APPLICATION LOOP
        // ==========================================================

        boolean running = true;

        while (running) {

            boolean authenticated = loginView.run();

            if (!authenticated) {
                running = false;
                continue;
            }

            // Display the role-based main menu
            mainMenuView.run();

        }

        System.out.println("\nExiting the application...");
        System.out.println("Thank you for using the University Enrollment System!");

        input.close();

    }
}
package com.joysistvi.univenrollmentapp;

import java.util.Scanner;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.controller.UserController;
import com.joysistvi.univenrollmentapp.repository.UserRepository;
import com.joysistvi.univenrollmentapp.repository.UserRepositoryImpl;
import com.joysistvi.univenrollmentapp.service.UserService;
import com.joysistvi.univenrollmentapp.service.UserServiceImpl;
import com.joysistvi.univenrollmentapp.utils.MessagePrinter;
import com.joysistvi.univenrollmentapp.view.LoginView;
import com.joysistvi.univenrollmentapp.view.MainMenuView;

// Main Class
// Entry point of the University Enrollment Application
public final class App {

    // Shared Scanner instance
    private static final Scanner INPUT = new Scanner(System.in);

    // Prevent instantiation
    private App() {
    }

    public static void main(String[] args) {

        try {

            startApplication();

        } finally {

            INPUT.close();

        }

    }

    // Starts the application
    private static void startApplication() {

        // ==========================================================
        // DATABASE CONNECTION
        // ==========================================================

        DbConnection dbConnection = new DbConnection();

        // ==========================================================
        // DEPENDENCY INJECTION
        // ==========================================================

        UserRepository userRepository =
                new UserRepositoryImpl(dbConnection);

        UserService userService =
                new UserServiceImpl(userRepository);

        UserController userController =
                new UserController(userService);

        LoginView loginView =
                new LoginView(userController, INPUT);

        MainMenuView mainMenuView =
                new MainMenuView(INPUT);

        // ==========================================================
        // APPLICATION LOOP
        // ==========================================================

        while (true) {

            boolean authenticated =
                    loginView.run();

            if (!authenticated) {
                break;
            }

            mainMenuView.run();

        }

        MessagePrinter.info(
                "Thank you for using the University Enrollment System!");

        MessagePrinter.info(
                "Application terminated.");

    }
}

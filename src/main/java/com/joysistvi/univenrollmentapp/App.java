package com.joysistvi.univenrollmentapp;

import java.util.Scanner;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.controller.UserController;
import com.joysistvi.univenrollmentapp.repository.UserRepository;
import com.joysistvi.univenrollmentapp.repository.UserRepositoryImpl;
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
        // DEPENDENCY INJECTION
        // ==========================================================

        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserService userService = new UserServiceImpl(userRepository);
        UserController userController = new UserController(userService);

        LoginView loginView = new LoginView(userController, input);
        MainMenuView mainMenuView = new MainMenuView(input);

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

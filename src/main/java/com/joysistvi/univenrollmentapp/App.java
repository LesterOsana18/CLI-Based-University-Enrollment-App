package com.joysistvi.univenrollmentapp;

import java.util.Scanner;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.session.Session;
import com.joysistvi.univenrollmentapp.controller.*;
import com.joysistvi.univenrollmentapp.repository.*;
import com.joysistvi.univenrollmentapp.service.*;
import com.joysistvi.univenrollmentapp.view.*;

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

            showMainMenu();

            Session.logout();

        }

        System.out.println("\nExiting the application...");
        System.out.println("Thank you for using the University Enrollment System!");

        input.close();

    }

    // Display the main menu based on the logged-in user's role
    private static void showMainMenu() {

        switch (Session.getCurrentUser().getRole()) {

            case STUDENT:
                showStudentMenu();
                break;

            case REGISTRAR:
                showRegistrarMenu();
                break;

            case ADMIN:
                showAdminMenu();
                break;

        }

    }

    // ==========================================================
    // PLACEHOLDER MENUS
    // ==========================================================

    private static void showStudentMenu() {

        System.out.println("\n=================================");
        System.out.println("          STUDENT MENU");
        System.out.println("=================================");

        System.out.println("Feature not implemented yet.");
        System.out.println("TODO: Student Module");

    }

    private static void showRegistrarMenu() {

        System.out.println("\n=================================");
        System.out.println("         REGISTRAR MENU");
        System.out.println("=================================");

        System.out.println("Feature not implemented yet.");
        System.out.println("TODO: Registrar Module");

    }

    private static void showAdminMenu() {

        System.out.println("\n=================================");
        System.out.println("           ADMIN MENU");
        System.out.println("=================================");

        System.out.println("Feature not implemented yet.");
        System.out.println("TODO: Admin Module");

    }
}

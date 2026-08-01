package com.joysistvi.univenrollmentapp.view;

import java.util.Scanner;

import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.session.Session;
import com.joysistvi.univenrollmentapp.utils.ConsoleUtils;
import com.joysistvi.univenrollmentapp.utils.HeaderPrinter;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.MenuPrinter;
import com.joysistvi.univenrollmentapp.utils.MessagePrinter;
import com.joysistvi.univenrollmentapp.utils.ScreenUtils;

// View Class
// Displays the application's main menu after a successful login
public class MainMenuView {

    // Scanner object
    private final Scanner input;

    // Constructor
    public MainMenuView(Scanner scanner) {
        this.input = scanner;
    }

    // Display the Main Menu
    public void run() {

        while (Session.isLoggedIn()) {

            ScreenUtils.clearScreen();

            User currentUser = Session.getCurrentUser();

            HeaderPrinter.printHeader("UNIVERSITY ENROLLMENT SYSTEM");

            System.out.println("Logged in as : " + currentUser.getUsername());
            System.out.println("Role         : " + currentUser.getRole().getDisplayName());

            switch (currentUser.getRole()) {

                case ADMIN:
                    showAdminMenu();
                    break;

                case REGISTRAR:
                    showRegistrarMenu();
                    break;

                case STUDENT:
                    showStudentMenu();
                    break;

            }

        }

    }

    // ==========================================================
    // ADMIN MENU
    // ==========================================================

    private void showAdminMenu() {

        MenuPrinter.printMenu(
                "ADMIN MENU",
                "Logout",
                "Students",
                "Courses",
                "Departments",
                "Enrollments",
                "Employees",
                "Users");

        int choice = InputValidator.readMenuChoice(input, 0, 6);

        switch (choice) {

            case 1:
                new StudentView().displayMenu(input);
                break;
            case 2:
                new CourseView().displayMenu(input);
                break;
            case 3:
                new DepartmentView().displayMenu(input);
                break;
            case 4:
                new EnrollmentView().displayMenu(input);
                break;
            case 5:
                new EmployeeView().displayMenu(input);
                break;
            case 6:
                new UserView().displayMenu(input);
                break;

            case 0:

                logout();
                break;

        }

    }

    // ==========================================================
    // REGISTRAR MENU
    // ==========================================================

    private void showRegistrarMenu() {

        MenuPrinter.printMenu(
                "REGISTRAR MENU",
                "Logout",
                "Students",
                "Courses",
                "Enrollments");

        int choice = InputValidator.readMenuChoice(input, 0, 3);

        switch (choice) {

            case 1:
                new StudentView().displayMenu(input);
                break;
            case 2:
                featureNotImplemented();
                break;
            case 3:
                new EnrollmentView().displayMenu(input);
                break;

            case 0:

                logout();
                break;

        }

    }

    // ==========================================================
    // STUDENT MENU
    // ==========================================================

    private void showStudentMenu() {

        MenuPrinter.printMenu(
                "STUDENT MENU",
                "Logout",
                "View Courses",
                "My Enrollments");

        int choice = InputValidator.readMenuChoice(input, 0, 2);

        switch (choice) {

            case 1:
            case 2:

                featureNotImplemented();
                break;

            case 0:

                logout();
                break;

        }

    }

    // ==========================================================
    // HELPERS
    // ==========================================================

    private void featureNotImplemented() {

        MessagePrinter.warning("Feature not implemented yet.");
        MessagePrinter.info("This module is currently assigned as a TODO.");

        ConsoleUtils.pressEnterToContinue(input);

    }

    private void logout() {

        Session.logout();

        MessagePrinter.success("You have been logged out successfully.");

    }
}

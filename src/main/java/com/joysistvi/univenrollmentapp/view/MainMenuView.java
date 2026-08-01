package com.joysistvi.univenrollmentapp.view;

import java.util.Scanner;

// import com.joysistvi.univenrollmentapp.enums.Role;
import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.session.Session;

// View Class
// Displays the application's main menu after a successful login
public class MainMenuView {

    // Scanner object for user input
    private final Scanner input;

    // Constructor
    public MainMenuView(Scanner scanner) {
        this.input = scanner;
    }

    // Display the Main Menu
    public void run() {

        while (Session.isLoggedIn()) {

            User currentUser = Session.getCurrentUser();

            System.out.println("\n=========================================");
            System.out.println("      UNIVERSITY ENROLLMENT SYSTEM");
            System.out.println("=========================================");
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

        System.out.println("\n1. Students");
        System.out.println("2. Courses");
        System.out.println("3. Departments");
        System.out.println("4. Enrollments");
        System.out.println("5. Employees");
        System.out.println("6. Users");
        System.out.println("0. Logout");

        System.out.print("\nChoice: ");

        int choice = readMenuChoice();

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
                featureNotImplemented();
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

            default:

                System.out.println("\nInvalid menu option.");

        }

    }

    // ==========================================================
    // REGISTRAR MENU
    // ==========================================================

    private void showRegistrarMenu() {

        System.out.println("\n1. Students");
        System.out.println("2. Courses");
        System.out.println("3. Enrollments");
        System.out.println("0. Logout");

        System.out.print("\nChoice: ");

        int choice = readMenuChoice();

        switch (choice) {

            case 1:
                new StudentView().displayMenu(input);
                break;
            case 2:
            case 3:

                featureNotImplemented();
                break;

            case 0:

                logout();
                break;

            default:

                System.out.println("\nInvalid menu option.");

        }

    }

    // ==========================================================
    // STUDENT MENU
    // ==========================================================

    private void showStudentMenu() {

        System.out.println("\n1. View Courses");
        System.out.println("2. My Enrollments");
        System.out.println("0. Logout");

        System.out.print("\nChoice: ");

        int choice = readMenuChoice();

        switch (choice) {

            case 1:
            case 2:

                featureNotImplemented();
                break;

            case 0:

                logout();
                break;

            default:

                System.out.println("\nInvalid menu option.");

        }

    }

    // ==========================================================
    // HELPERS
    // ==========================================================

    private int readMenuChoice() {

        while (!input.hasNextInt()) {

            System.out.println("\nError: Please enter a valid menu number.");
            input.nextLine();
            System.out.print("Choice: ");

        }

        int choice = input.nextInt();
        input.nextLine();

        return choice;

    }

    private void featureNotImplemented() {

        System.out.println("\n=========================================");
        System.out.println("Feature not implemented yet.");
        System.out.println("This module is currently assigned as a TODO.");
        System.out.println("=========================================");

        System.out.print("\nPress Enter to continue...");
        input.nextLine();

    }

    private void logout() {

        Session.logout();

        System.out.println("\nYou have been logged out successfully.");

    }
}

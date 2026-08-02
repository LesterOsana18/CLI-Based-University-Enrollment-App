package com.joysistvi.univenrollmentapp.view;

import java.util.Scanner;

import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.session.Session;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.MenuPrinter;

public class MainMenuView {

    // Scanner for user input
    private final Scanner input;

    // Views for different functionalities
    private final StudentPortalView studentPortalView;
    private final StudentManagementView studentManagementView;
    private final CourseView courseView;
    private final DepartmentView departmentView;
    private final EnrollmentView enrollmentView;
    private final EmployeeView employeeView;
    private final UserView userView;
    private final PrerequisiteView prerequisiteView;

    public MainMenuView(
            Scanner input,
            StudentPortalView studentPortalView,
            StudentManagementView studentManagementView,
            CourseView courseView,
            DepartmentView departmentView,
            EnrollmentView enrollmentView,
            EmployeeView employeeView,
            UserView userView,
            PrerequisiteView prerequisiteView) {

        this.input = input;
        this.studentPortalView = studentPortalView;
        this.studentManagementView = studentManagementView;
        this.courseView = courseView;
        this.departmentView = departmentView;
        this.enrollmentView = enrollmentView;
        this.employeeView = employeeView;
        this.userView = userView;
        this.prerequisiteView = prerequisiteView;
    }

    public void displayMenu() {

        while (Session.isLoggedIn()) {

            User currentUser = Session.getCurrentUser();

            System.out.println("\n==========================================");
            System.out.println(" UNIVERSITY ENROLLMENT SYSTEM");
            System.out.println("==========================================");
            System.out.println("Logged in as : " + currentUser.getUsername());
            System.out.println("Role         : " + currentUser.getRole().getDisplayName());

            switch (currentUser.getRole()) {

                case ADMIN -> showAdminMenu(currentUser);

                case REGISTRAR -> showRegistrarMenu(currentUser);

                case STUDENT -> studentPortalView.displayMenu(
                        currentUser.getId());

            }

        }

    }

    // Show the administrator menu
    private void showAdminMenu(User currentUser) {

        boolean back = false;

        while (!back) {

            MenuPrinter.printMenu(
                "Administrator Menu", 
                "Logout",
                "Student Management",
                "Course Management",
                "Department Management",
                "Enrollment Directory",
                "Employee Management",
                "User Management",
                "Prerequisite Management");

            int choice = InputValidator.readMenuChoice(input, 0, 7);

            switch (choice) {
                case 1 -> studentManagementView.displayMenu();
                case 2 -> courseView.displayMenu();
                case 3 -> departmentView.displayMenu();
                case 4 -> enrollmentView.displayMenu();
                case 5 -> employeeView.displayMenu();
                case 6 -> userView.displayMenu();
                case 7 -> prerequisiteView.displayMenu();
                case 0 -> {
                        back = true;
                        logout();
                    }
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    // Show the registrar menu
    private void showRegistrarMenu(User currentUser) {

        boolean back = false;

        while (!back) {

            MenuPrinter.printMenu(
                "Registrar Menu",
                "Logout",
                "Student Management",
                "Course Management",
                "Enrollment Directory",
                "Prerequisite Management");

            int choice = InputValidator.readMenuChoice(input, 0, 4);

            switch (choice) {
                case 1 -> studentManagementView.displayMenu();
                case 2 -> courseView.displayMenu();
                case 3 -> enrollmentView.displayMenu();
                case 4 -> prerequisiteView.displayMenu();
                case 0 -> {
                        back = true;
                        logout();
                    }
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    private void logout() {

        Session.logout();
        System.out.println("Logged out successfully!");

    }

}
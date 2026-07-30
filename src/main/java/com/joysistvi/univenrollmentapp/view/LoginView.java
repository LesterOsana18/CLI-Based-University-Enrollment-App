package com.joysistvi.univenrollmentapp.view;

import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.UserController;
import com.joysistvi.univenrollmentapp.enums.Role;
import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.session.Session;
import com.joysistvi.univenrollmentapp.utils.InputValidator;

// View Class
// Handles user login and registration
public class LoginView {

    // Dependency Injection
    private final UserController userController;

    // Scanner object
    private final Scanner input;

    // Constructor
    public LoginView(UserController userController,
                     Scanner scanner) {

        this.userController = userController;
        this.input = scanner;

    }

    // Display the Login Menu
    public boolean run() {

        while (true) {

            System.out.println("\n=========================================");
            System.out.println(" UNIVERSITY ENROLLMENT SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");

            System.out.print("\nChoice: ");

            int choice =
                    InputValidator.readMenuChoice(input);

            switch (choice) {

                case 1:

                    if (login()) {
                        return true;
                    }

                    break;

                case 2:

                    register();
                    break;

                case 0:

                    return false;

                default:

                    System.out.println(
                            "\nInvalid menu option.");

            }

        }

    }

    // ==========================================================
    // LOGIN
    // ==========================================================

    private boolean login() {

        System.out.println("\n=== User Login ===");

        String username =
                InputValidator.readRequiredString(
                        input,
                        "Username");

        String password =
                InputValidator.readRequiredString(
                        input,
                        "Password");

        User user =
                userController.login(username, password);

        if (user == null) {

            System.out.println(
                    "\nInvalid username or password.");

            return false;

        }

        Session.login(user);

        System.out.println(
                "\nWelcome, "
                        + user.getUsername()
                        + "!");

        return true;

    }

    // ==========================================================
    // REGISTER
    // ==========================================================

    private void register() {

        System.out.println(
                "\n=== User Registration ===");

        String username =
                InputValidator.readRequiredString(
                        input,
                        "Username");

        String password =
                InputValidator.readRequiredString(
                        input,
                        "Password");

        System.out.print("Confirm Password: ");
        String confirmPassword =
                input.nextLine();

        if (!password.equals(confirmPassword)) {

            System.out.println(
                    "\nPasswords do not match.");

            return;

        }

        System.out.println("\nSelect Role:");
        System.out.println("1. Student");
        System.out.println("2. Registrar");
        System.out.println("3. Administrator");

        Role role = null;

        while (role == null) {

            System.out.print("\nChoice: ");

            int choice =
                    InputValidator.readMenuChoice(input);

            switch (choice) {

                case 1:
                    role = Role.STUDENT;
                    break;

                case 2:
                    role = Role.REGISTRAR;
                    break;

                case 3:
                    role = Role.ADMIN;
                    break;

                default:

                    System.out.println(
                            "\nInvalid option.");

            }

        }

        User user =
                new User(username,
                         password,
                         role);

        if (userController.register(user)) {

            System.out.println(
                    "\nRegistration successful!");

        } else {

            System.out.println(
                    "\nUsername already exists.");

        }

    }
}

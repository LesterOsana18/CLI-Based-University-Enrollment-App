package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.UserController;
import com.joysistvi.univenrollmentapp.enums.Role;
import com.joysistvi.univenrollmentapp.model.User;
import com.joysistvi.univenrollmentapp.session.Session;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.PasswordUtils;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

public class UserView {

    private Scanner input;

    private final UserController controller;

    public UserView(UserController controller) {
        this.controller = controller;
    }

    public void displayMenu(Scanner input) {

        this.input = input;

        while (true) {

            System.out.println("\n===== User Management =====");
            System.out.println("1. View Users");
            System.out.println("2. Create User");
            System.out.println("3. Update User");
            System.out.println("4. Archive User");
            System.out.println("5. View Archived Users");
            System.out.println("6. Restore User");
            System.out.println("7. Delete User");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            switch (InputValidator.readPositiveInt(input, "Selected menu option")) {

                case 1 -> displayUsers(controller.getAllUsers());
                case 2 -> createUser();
                case 3 -> updateUser();
                case 4 -> archiveUser();
                case 5 -> displayUsers(controller.getArchivedUsers());
                case 6 -> restoreUser();
                case 7 -> deleteUser();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid menu option.");

            }

        }

    }

    private void createUser() {

        System.out.print("Enter Username: ");
        String username = input.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        String validationMessage =
                PasswordUtils.getPasswordValidationMessage(password);

        if (validationMessage != null) {

            System.out.println(validationMessage);
            return;

        }

        Role role = readRole();

        if (username.isBlank() || role == null) {

            System.out.println("Username and role are required.");
            return;

        }

        User user = new User(username, password, role);

        if (controller.createUser(user)) {

            System.out.println("User created successfully.");

        } else {

            System.out.println("Failed to create user.");

        }

    }

    private void updateUser() {

        List<User> users = controller.getAllUsers();

        displayUsers(users);

        if (users.isEmpty()) {
            return;
        }

        System.out.print("Enter User ID: ");
        User existingUser = findUser(users, InputValidator.readPositiveInt(input, "User ID"));

        if (existingUser == null) {

            System.out.println("User not found.");
            return;

        }

        System.out.print("Enter New Username: ");
        String username = input.nextLine().trim();

        Role role = readRole();

        if (username.isBlank() || role == null) {

            System.out.println("Username and role are required.");
            return;

        }

        User updatedUser = new User(
                existingUser.getId(),
                username,
                null,
                role,
                existingUser.getCreatedAt());

        if (controller.updateUser(updatedUser)) {

            System.out.println("User updated successfully.");

        } else {

            System.out.println("Failed to update user.");

        }

    }

    private void archiveUser() {

        List<User> users = controller.getAllUsers();

        displayUsers(users);

        if (users.isEmpty()) {
            return;
        }

        System.out.print("Enter User ID: ");
        int id = InputValidator.readPositiveInt(input, "User ID");

        User currentUser = Session.getCurrentUser();

        if (currentUser != null && currentUser.getId() == id) {

            System.out.println("You cannot archive the account currently signed in.");
            return;

        }

        if (controller.archiveUser(id)) {

            System.out.println("User archived successfully.");

        } else {

            System.out.println("Failed to archive user.");

        }

    }

    private void restoreUser() {

        List<User> users = controller.getArchivedUsers();

        displayUsers(users);

        if (users.isEmpty()) {
            return;
        }

        System.out.print("Enter User ID: ");
        int id = InputValidator.readPositiveInt(input, "User ID");

        if (controller.restoreUser(id)) {

            System.out.println("User restored successfully.");

        } else {

            System.out.println("Failed to restore user.");

        }

    }

    private void deleteUser() {

        List<User> users = controller.getArchivedUsers();

        displayUsers(users);

        if (users.isEmpty()) {
            return;
        }

        System.out.print("Enter User ID: ");
        int id = InputValidator.readPositiveInt(input, "User ID");

        if (controller.deleteUser(id)) {

            System.out.println("User deleted successfully.");

        } else {

            System.out.println("Failed to delete user.");

        }

    }

    private Role readRole() {

        System.out.println("Select Role");
        System.out.println("1. Student");
        System.out.println("2. Registrar");
        System.out.println("3. Administrator");
        System.out.print("Choice: ");

        return switch (InputValidator.readPositiveInt(input, "Selected role option")) {

            case 1 -> Role.STUDENT;
            case 2 -> Role.REGISTRAR;
            case 3 -> Role.ADMIN;

            default -> {

                System.out.println("Invalid role.");
                yield null;

            }

        };

    }

    private void displayUsers(List<User> users) {

        if (users.isEmpty()) {

            TableFormatter.printNoRecordsFound();
            return;

        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-20s %-15s %-22s%n",
                "ID",
                "Username",
                "Role",
                "Created At");

        TableFormatter.printDivider();

        for (User user : users) {

            System.out.printf(
                    "%-5d %-20s %-15s %-22s%n",
                    user.getId(),
                    user.getUsername(),
                    user.getRole().getDisplayName(),
                    user.getCreatedAt());

        }

        TableFormatter.printDivider();
        TableFormatter.printTotalRecords(users.size());

    }

    private User findUser(List<User> users, int id) {

        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);

    }

}
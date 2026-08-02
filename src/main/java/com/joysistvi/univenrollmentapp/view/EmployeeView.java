package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.EmployeeController;
import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.MenuPrinter;
import com.joysistvi.univenrollmentapp.utils.MessagePrinter;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

// View Class
// Handles the user interface for employee management
public class EmployeeView {

    // Scanner object for user input
    private final Scanner input;

    // Controller for employee operations
    private final EmployeeController controller;

    // Constructor
    public EmployeeView(
            Scanner input, 
            EmployeeController controller) {

        this.input = input;
        this.controller = controller;
    }

    // Displays the main menu for employee management
    public void displayMenu() {

        boolean back = false;

        while (!back) {

            MenuPrinter.printMenu(
                    "Employee Management",
                    "Back",
                    "View Employees",
                    "Create Employee",
                    "Update Employee",
                    "Archive Employee",
                    "View Archived Employees",
                    "Restore Employee",
                    "Reset Password");

            int choice = InputValidator.readMenuChoice(input, 0, 7);

            switch (choice) {
                case 1 -> displayAllEmployees();
                case 2 -> createEmployee();
                case 3 -> updateEmployee();
                case 4 -> archiveEmployee();
                case 5 -> displayArchivedEmployees();
                case 6 -> restoreEmployee();
                case 7 -> resetPassword();
                case 0 -> back = true;
                default -> MessagePrinter.error("Invalid menu option.");
            }
        }
    }

    // Display all active employees
    public void displayAllEmployees() {
        printEmployees(controller.getAllEmployees());
    }

    // Display archived employees
    public void displayArchivedEmployees() {
        printEmployees(controller.getArchivedEmployees());
    }

    // Create employee
    private void createEmployee() {

        Employee employee = readEmployeeDetails(0);

        if (employee == null) {
            return;
        }

        System.out.print("Enter Password: ");
        String password = input.nextLine().trim();

        if (password.isBlank()) {
            System.out.println("Password cannot be empty.");
            return;
        }

        if (controller.createEmployee(employee, password)) {

            System.out.println("Employee created successfully.");

        } else {

            System.out.println("Failed to create employee.");

        }
    }

    // Update employee
    private void updateEmployee() {

        displayAllEmployees();

        System.out.print("Enter Employee Record ID: ");
        int id = readInt();

        Employee employee = readEmployeeDetails(id);

        if (employee == null) {
            return;
        }

        if (controller.updateEmployee(employee)) {

            System.out.println("Employee updated successfully.");

        } else {

            System.out.println("Failed to update employee.");

        }
    }

    // Archive employee
    private void archiveEmployee() {

        displayAllEmployees();

        System.out.print("Enter Employee Record ID: ");
        int id = readInt();

        if (controller.archiveEmployee(id)) {
            System.out.println("Employee archived successfully.");
        } else {
            System.out.println("Failed to archive employee.");
        }
    }

    // Restore employee
    private void restoreEmployee() {

        displayArchivedEmployees();

        System.out.print("Enter Employee Record ID: ");
        int id = readInt();

        if (controller.restoreEmployee(id)) {
            System.out.println("Employee restored successfully.");
        } else {
            System.out.println("Failed to restore employee.");
        }
    }

    // Reset employee password
    private void resetPassword() {

        displayAllEmployees();

        System.out.print("Enter Employee Record ID: ");
        int id = readInt();

        Employee employee = controller.getEmployeeById(id);

        if (employee == null) {

            System.out.println("Employee not found.");
            return;

        }

        System.out.print("Enter New Password: ");
        String password = input.nextLine().trim();

        System.out.print("Confirm New Password: ");
        String confirmPassword = input.nextLine().trim();

        if (!password.equals(confirmPassword)) {

            System.out.println("Passwords do not match.");
            return;

        }

        if (password.isBlank()) {

            System.out.println("Password cannot be empty.");
            return;

        }

        if (controller.resetPassword(employee.getUserId(), password)) {

            System.out.println("Password reset successfully.");

        } else {

            System.out.println("Failed to reset password.");

        }

    }

    // Read employee information
    private Employee readEmployeeDetails(int id) {

        System.out.print("Enter Employee ID: ");
        String employeeId = input.nextLine().trim();

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine().trim();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine().trim();

        System.out.print("Enter Username: ");
        String username = input.nextLine().trim();

        Position position = readPosition();

        if (employeeId.isBlank()
                || firstName.isBlank()
                || lastName.isBlank()
                || username.isBlank()
                || position == null) {

            System.out.println("All fields are required.");
            return null;
        }

        return new Employee(
                id,
                employeeId,
                firstName,
                lastName,
                position,
                0,
                username,
                Status.ACTIVE);
    }

    // Read employee position
    private Position readPosition() {

        System.out.println("Select Position");
        System.out.println("1. Registrar");
        System.out.println("2. Administrator");
        System.out.print("Choice: ");

        return switch (readInt()) {
            case 1 -> Position.REGISTRAR;
            case 2 -> Position.ADMIN;
            default -> {
                System.out.println("Invalid position.");
                yield null;
            }
        };
    }

    // Print employee table
    private void printEmployees(List<Employee> employees) {

        if (employees.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-15s %-25s %-20s %-15s %-12s%n",
                "ID",
                "Employee ID",
                "Name",
                "Username",
                "Position",
                "Status");

        TableFormatter.printDivider();

        for (Employee employee : employees) {

            System.out.printf(
                    "%-5d %-15s %-25s %-20s %-15s %-12s%n",
                    employee.getId(),
                    employee.getEmployeeId(),
                    employee.getFirstName() + " " + employee.getLastName(),
                    employee.getUsername(),
                    employee.getPosition().getDisplayName(),
                    employee.getStatus().getDisplayName());

        }

        TableFormatter.printDivider();
        TableFormatter.printTotalRecords(employees.size());
    }

    // Read integer safely
    private int readInt() {

        while (!input.hasNextInt()) {

            System.out.println("Please enter a valid number.");
            input.nextLine();
            System.out.print("Choice: ");

        }

        int value = input.nextInt();
        input.nextLine();

        return value;
    }
}
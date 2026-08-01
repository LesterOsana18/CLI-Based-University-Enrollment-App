package com.joysistvi.univenrollmentapp.view;

import com.joysistvi.univenrollmentapp.controller.EmployeeController;
import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

import java.util.List;
import java.util.Scanner;

public class EmployeeView {

    private Scanner input;
    private final EmployeeController controller;

    public EmployeeView(EmployeeController controller) {
        this.controller = controller;
    }

    public void displayMenu(Scanner input) {
        this.input = input;

        System.out.println("===== Employee Management =====");
        System.out.println("1. View Active Employees");
        System.out.println("2. Create Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Deactivate Employee");
        System.out.println("5. View Inactive Employees");
        System.out.println("6. Reactivate Employee");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        switch (readInt()) {
            case 1 -> displayActiveEmployees();
            case 2 -> createEmployee();
            case 3 -> updateEmployee();
            case 4 -> deactivateEmployee();
            case 5 -> displayInactiveEmployees();
            case 6 -> reactivateEmployee();
            case 0 -> {
            }
            default -> System.out.println("Invalid menu option.");
        }
    }

    private void displayActiveEmployees() {
        printEmployees(controller.getActiveEmployees());
    }

    private void displayInactiveEmployees() {
        printEmployees(controller.getArchivedEmployees());
    }

    private void createEmployee() {

        System.out.print("Enter Employee ID: ");
        String employeeId = input.nextLine().trim();

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine().trim();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine().trim();

        System.out.print("Enter Username: ");
        String username = input.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        Position position = readPosition();

        if (employeeId.isBlank()
                || firstName.isBlank()
                || lastName.isBlank()
                || username.isBlank()
                || password.isBlank()
                || position == null) {

            System.out.println("All fields are required.");
            return;
        }

        if (controller.createEmployee(
                employeeId,
                firstName,
                lastName,
                username,
                password,
                position)) {

            System.out.println("Employee created successfully.");

        } else {

            System.out.println("Failed to create employee.");

        }
    }

    private void updateEmployee() {

        List<Employee> employees = controller.getActiveEmployees();

        printEmployees(employees);

        if (employees.isEmpty()) {
            return;
        }

        System.out.print("Enter Employee Record ID: ");
        Employee employee = findEmployee(employees, readInt());

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter Employee ID: ");
        String employeeId = input.nextLine().trim();

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine().trim();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine().trim();

        Position position = readPosition();

        if (employeeId.isBlank()
                || firstName.isBlank()
                || lastName.isBlank()
                || position == null) {

            System.out.println("All fields are required.");
            return;
        }

        if (controller.updateEmployee(
                employee,
                employeeId,
                firstName,
                lastName,
                position,
                employee.getStatus())) {

            System.out.println("Employee updated successfully.");

        } else {

            System.out.println("Failed to update employee.");

        }
    }

    private void deactivateEmployee() {

        List<Employee> employees = controller.getActiveEmployees();

        printEmployees(employees);

        if (employees.isEmpty()) {
            return;
        }

        System.out.print("Enter Employee Record ID: ");

        if (controller.deactivateEmployee(readInt())) {

            System.out.println("Employee deactivated successfully.");

        } else {

            System.out.println("Failed to deactivate employee.");

        }
    }

    private void reactivateEmployee() {

        List<Employee> employees = controller.getArchivedEmployees();

        printEmployees(employees);

        if (employees.isEmpty()) {
            return;
        }

        System.out.print("Enter Employee Record ID: ");

        if (controller.reactivateEmployee(readInt())) {

            System.out.println("Employee reactivated successfully.");

        } else {

            System.out.println("Failed to reactivate employee.");

        }
    }

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

    private void printEmployees(List<Employee> employees) {

        if (employees.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        printDivider();

        System.out.printf(
                "%-5s %-15s %-25s %-20s %-15s %-12s%n",
                "ID",
                "Employee ID",
                "Name",
                "Username",
                "Position",
                "Status");

        printDivider();

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

        TableFormatter.printTotalRecords(employees.size());
    }

    private Employee findEmployee(List<Employee> employees, int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

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

    private void printDivider() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }
}
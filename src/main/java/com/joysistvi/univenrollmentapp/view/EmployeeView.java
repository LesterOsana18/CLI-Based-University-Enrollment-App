package com.joysistvi.univenrollmentapp.view;

import com.joysistvi.univenrollmentapp.controller.EmployeeController;
import com.joysistvi.univenrollmentapp.enums.Position;
import com.joysistvi.univenrollmentapp.model.Employee;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;
import java.util.List;
import java.util.Scanner;

public class EmployeeView {
    private Scanner input;
    private final EmployeeController controller = new EmployeeController();

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
            case 0 -> { }
            default -> System.out.println("Invalid menu option.");
        }
    }

    private void displayActiveEmployees() {
        printEmployees(controller.getActiveEmployees(), "active employees");
    }

    private void displayInactiveEmployees() {
        printEmployees(controller.getInactiveEmployees(), "inactive employees");
    }

    private void createEmployee() {
        System.out.print("Enter employee ID: ");
        String employeeId = input.nextLine().trim();
        System.out.print("Enter first name: ");
        String firstName = input.nextLine().trim();
        System.out.print("Enter last name: ");
        String lastName = input.nextLine().trim();
        System.out.print("Enter username: ");
        String username = input.nextLine().trim();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        Position position = readPosition();
        if (employeeId.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
                || username.isEmpty() || password.isBlank() || position == null) {
            System.out.println("All employee details are required.");
            return;
        }

        if (controller.createEmployee(employeeId, firstName, lastName, username, password, position)) {
            System.out.println("Employee and user account created successfully.");
        } else {
            System.out.println("Failed to create employee. The employee ID or username may already exist.");
        }
    }

    private void updateEmployee() {
        List<Employee> employees = controller.getActiveEmployees();
        printEmployees(employees, "active employees");
        if (employees.isEmpty()) return;
        System.out.print("Enter employee record ID to update: ");
        Employee employee = findEmployee(employees, readInt());
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter employee ID: ");
        String employeeId = input.nextLine().trim();
        System.out.print("Enter first name: ");
        String firstName = input.nextLine().trim();
        System.out.print("Enter last name: ");
        String lastName = input.nextLine().trim();
        Position position = readPosition();
        if (employeeId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || position == null) {
            System.out.println("All employee details are required.");
            return;
        }

        if (controller.updateEmployee(employee, employeeId, firstName, lastName, position, employee.getStatus())) {
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Failed to update employee. The employee ID may already exist.");
        }
    }

    private void deactivateEmployee() {
        List<Employee> employees = controller.getActiveEmployees();
        printEmployees(employees, "active employees");
        if (employees.isEmpty()) return;
        System.out.print("Enter employee record ID to deactivate: ");
        if (controller.deactivateEmployee(readInt())) {
            System.out.println("Employee deactivated successfully.");
        } else {
            System.out.println("Failed to deactivate employee. Please check the ID and try again.");
        }
    }

    private void reactivateEmployee() {
        List<Employee> employees = controller.getInactiveEmployees();
        printEmployees(employees, "inactive employees");
        if (employees.isEmpty()) return;
        System.out.print("Enter employee record ID to reactivate: ");
        if (controller.reactivateEmployee(readInt())) {
            System.out.println("Employee reactivated successfully.");
        } else {
            System.out.println("Failed to reactivate employee. Please check the ID and try again.");
        }
    }

    private Position readPosition() {
        System.out.println("Select position: 1. Registrar  2. Administrator");
        System.out.print("Enter position: ");
        return switch (readInt()) {
            case 1 -> Position.REGISTRAR;
            case 2 -> Position.ADMIN;
            default -> {
                System.out.println("Invalid position.");
                yield null;
            }
        };
    }

    private void printEmployees(List<Employee> employees, String label) {
        if (employees.isEmpty()) {
            TableFormatter.printNoRecordsFound(label);
            return;
        }
        TableFormatter.printDivider();
        System.out.printf("%-5s %-15s %-20s %-15s %-15s %-12s%n",
                "ID", "Employee ID", "Name", "Username", "Position", "Status");
        TableFormatter.printDivider();
        for (Employee employee : employees) {
            System.out.printf("%-5d %-15s %-20s %-15s %-15s %-12s%n",
                    employee.getId(), employee.getEmployeeId(),
                    employee.getFirstName() + " " + employee.getLastName(),
                    employee.getUsername(), employee.getPosition().getDisplayName(),
                    employee.getStatus().getDisplayName());
        }
        TableFormatter.printTotalRecords(label, employees.size());
    }

    private Employee findEmployee(List<Employee> employees, int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
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
}

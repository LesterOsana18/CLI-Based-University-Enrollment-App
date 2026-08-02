package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.DepartmentController;
import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.utils.ConsoleUtils;
import com.joysistvi.univenrollmentapp.utils.HeaderPrinter;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.MessagePrinter;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

// View Class
// Displays the Department Management menu
public class DepartmentView {

    // Scanner object for user input
    private final Scanner input;

    // Controller for department operations
    private final DepartmentController controller;

    // Constructor
    public DepartmentView(
            Scanner input,
            DepartmentController controller) {

        this.input = input;
        this.controller = controller;

    }

    // Display Department Menu
    public void displayMenu() {

        boolean back = false;

        while (!back) {

            HeaderPrinter.printHeader("Department Management");

            System.out.println("1. View All Departments");
            System.out.println("2. Create Department");
            System.out.println("3. Update Department");
            System.out.println("4. Archive Department");
            System.out.println("5. View Archived Departments");
            System.out.println("6. Restore Department");
            System.out.println("0. Back");

            int choice = InputValidator.readMenuChoice(input);

            switch (choice) {

                case 1 -> displayAllDepartments();

                case 2 -> createDepartment();

                case 3 -> updateDepartment();

                case 4 -> archiveDepartment();

                case 5 -> displayArchivedDepartments();

                case 6 -> restoreDepartment();

                case 0 -> back = true;

                default -> MessagePrinter.error("Invalid menu option.");

            }

        }

    }

    // Display all active departments
    private void displayAllDepartments() {

        printDepartments(controller.getAllDepartments());

        ConsoleUtils.pressEnterToContinue(input);

    }

    // Display archived departments
    private void displayArchivedDepartments() {

        printDepartments(controller.getArchivedDepartments());

        ConsoleUtils.pressEnterToContinue(input);

    }

    // Create a new department
    private void createDepartment() {

        String departmentName =
                InputValidator.readRequiredString(
                        input,
                        "Department Name");

        Department department =
                new Department(
                        0,
                        departmentName);

        if (controller.createDepartment(department)) {

            MessagePrinter.success(
                    "Department created successfully.");

        } else {

            MessagePrinter.error(
                    "Failed to create department.");

        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // Update an existing department
    private void updateDepartment() {

        displayAllDepartments();

        int id =
                InputValidator.readPositiveInt(
                        input,
                        "Department ID");

        String departmentName =
                InputValidator.readRequiredString(
                        input,
                        "New Department Name");

        Department department =
                new Department(
                        id,
                        departmentName);

        if (controller.updateDepartment(department)) {

            MessagePrinter.success(
                    "Department updated successfully.");

        } else {

            MessagePrinter.error(
                    "Failed to update department.");

        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // Archive a department
    private void archiveDepartment() {

        displayAllDepartments();

        int id =
                InputValidator.readPositiveInt(
                        input,
                        "Department ID");

        if (controller.archiveDepartment(id)) {

            MessagePrinter.success(
                    "Department archived successfully.");

        } else {

            MessagePrinter.error(
                    "Failed to archive department.");

        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // Restore an archived department
    private void restoreDepartment() {

        displayArchivedDepartments();

        int id =
                InputValidator.readPositiveInt(
                        input,
                        "Department ID");

        if (controller.restoreDepartment(id)) {

            MessagePrinter.success(
                    "Department restored successfully.");

        } else {

            MessagePrinter.error(
                    "Failed to restore department.");

        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // Print departments
    private void printDepartments(
            List<Department> departments) {

        if (departments.isEmpty()) {

            TableFormatter.printNoRecordsFound();

            return;

        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-35s%n",
                "ID",
                "Department Name");

        TableFormatter.printDivider();

        for (Department department : departments) {

            System.out.printf(
                    "%-5d %-35s%n",
                    department.getId(),
                    department.getDepartmentName());

        }

        TableFormatter.printDivider();

        TableFormatter.printTotalRecords(
                departments.size());

    }

}
package com.joysistvi.univenrollmentapp.view;

import com.joysistvi.univenrollmentapp.controller.DepartmentController;
import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

import java.util.List;
import java.util.Scanner;

public class DepartmentView {

    private Scanner input;
    private final DepartmentController controller;

    public DepartmentView(DepartmentController controller) {
        this.controller = controller;
    }

    public void displayMenu(Scanner input) {
        this.input = input;

        System.out.println("===== Department Management =====");
        System.out.println("1. View All Departments");
        System.out.println("2. Create Department");
        System.out.println("3. Update Department");
        System.out.println("4. Archive Department");
        System.out.println("5. View Archived Departments");
        System.out.println("6. Restore Department");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        switch (readInt()) {
            case 1 -> displayAllDepartments();
            case 2 -> createDepartment();
            case 3 -> updateDepartment();
            case 4 -> archiveDepartment();
            case 5 -> displayArchivedDepartments();
            case 6 -> restoreDepartment();
            case 0 -> {
            }
            default -> System.out.println("Invalid menu option.");
        }
    }

    public void displayAllDepartments() {
        printDepartments(controller.getAllDepartments());
    }

    public void displayArchivedDepartments() {
        printDepartments(controller.getArchivedDepartments());
    }

    private void createDepartment() {
        String departmentName = readDepartmentName("Enter department name: ");

        if (departmentName == null) {
            return;
        }

        if (controller.createDepartment(departmentName)) {
            System.out.println("Department created successfully.");
        } else {
            System.out.println("Failed to create department.");
        }
    }

    private void updateDepartment() {
        displayAllDepartments();

        System.out.print("Enter department ID to update: ");
        int id = readInt();

        String departmentName = readDepartmentName("Enter new department name: ");

        if (departmentName == null) {
            return;
        }

        if (controller.updateDepartment(id, departmentName)) {
            System.out.println("Department updated successfully.");
        } else {
            System.out.println("Failed to update department.");
        }
    }

    private void archiveDepartment() {
        displayAllDepartments();

        System.out.print("Enter department ID to archive: ");
        int id = readInt();

        if (controller.archiveDepartment(id)) {
            System.out.println("Department archived successfully.");
        } else {
            System.out.println("Failed to archive department.");
        }
    }

    private void restoreDepartment() {
        displayArchivedDepartments();

        System.out.print("Enter department ID to restore: ");
        int id = readInt();

        if (controller.restoreDepartment(id)) {
            System.out.println("Department restored successfully.");
        } else {
            System.out.println("Failed to restore department.");
        }
    }

    private void printDepartments(List<Department> departments) {

        if (departments.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        printDivider();

        System.out.printf(
                "%-5s %-35s%n",
                "ID",
                "Department Name");

        printDivider();

        for (Department department : departments) {
            System.out.printf(
                    "%-5d %-35s%n",
                    department.getId(),
                    department.getDepartmentName());
        }

        printDivider();
        TableFormatter.printTotalRecords(departments.size());
    }

    private String readDepartmentName(String prompt) {

        System.out.print(prompt);

        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Department name cannot be empty.");
            return null;
        }

        return name;
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
        System.out.println("------------------------------------------------");
    }
}
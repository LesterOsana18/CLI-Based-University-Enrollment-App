package com.joysistvi.univenrollmentapp.view;


import com.joysistvi.univenrollmentapp.controller.DepartmentController;
import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

import java.util.List;
import java.util.Scanner;

public class DepartmentView {
    private Scanner input;
    private final DepartmentController controller = new DepartmentController();

    public void displayMenu(Scanner input){
        this.input = input;
        System.out.println("===== Department =====");
        System.out.println("1 . View All Department");
        System.out.println("2 . Create Department");
        System.out.println("3 . Update Department");
        System.out.println("4 . Delete Department");
        System.out.println("5 . View Archived");
        System.out.println("0 . Back");
        System.out.print("Enter Choice: ");
        int choice = readMenuChoice();
        
        switch(choice){
            case 1 -> displayAllDepartments();
            case 2 -> createDepartment();
            case 3 -> updateDepartment();
            case 4 -> deleteDepartment();
            case 5 -> displayArchivedDepartments();
            case 0 -> {}
            default -> System.out.println("Invalid menu option.");
        }
        
    }
    
    public void displayAllDepartments(){
        printDepartments(controller.getAllDepartments(), "active departments");
    }

    public void displayArchivedDepartments() {
        printDepartments(controller.getArchivedDepartments(), "archived departments");
    }

    public void createDepartment() {
        String name = readDepartmentName("Enter department name: ");
        if (name == null) return;

        if (controller.createDepartment(name)) {
            System.out.println("Department created successfully.");
        } else {
            System.out.println("Failed to create department. A department with that name may already exist.");
        }
    }

    public void updateDepartment(){
        displayAllDepartments();
        System.out.print("Enter department ID to update: ");
        int id = readMenuChoice();
        String name = readDepartmentName("Enter new department name: ");
        if (name == null) return;

        boolean success = controller.updateDepartment(id, name);
        if (success) {
            System.out.println("Department updated successfully.");
        } else {
            System.out.println("Failed to update department. Please check the ID and try again.");
        }
    }

    public void deleteDepartment() {
        displayAllDepartments();
        System.out.print("Enter department ID to archive: ");
        int id = readMenuChoice();

        if (controller.deleteDepartment(id)) {
            System.out.println("Department archived successfully.");
        } else {
            System.out.println("Failed to archive department. Please check the ID and try again.");
        }
    }

    private void printDepartments(List<Department> departments, String label) {
        if (departments.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        printDivider();
        System.out.printf("%-5s %-30s%n", "ID", "Department Name");
        printDivider();
        for (Department department : departments) {
            System.out.printf("%-5d %-30s%n", department.getId(), department.getDepartmentName());
        }
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

    private void printDivider() {
        System.out.println("----------------------------------------");
    }
}

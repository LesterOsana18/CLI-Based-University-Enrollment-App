package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.controller.StudentManagementController;
import com.joysistvi.univenrollmentapp.controller.DepartmentController;
import com.joysistvi.univenrollmentapp.enums.Status;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.MenuPrinter;
import com.joysistvi.univenrollmentapp.utils.MessagePrinter;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

public class StudentManagementView {

    // Create Scanner object for user input
    private final Scanner input;

    // Controller for handling student management operations
    private final StudentManagementController controller;
    private final DepartmentController departmentController;

    // Constructor
    public StudentManagementView(
            Scanner input, 
            StudentManagementController controller,
            DepartmentController departmentController) {

        this.input = input;
        this.controller = controller;
        this.departmentController = departmentController;

    }

    // Displays the main menu for student management
    public void displayMenu() {

        boolean back = false;

        while (!back) {

            MenuPrinter.printMenu(
                "Student Management", 
                "Back", 
            "View All Students",
                "Search Student", 
                "Add Student",
                "Update Student", 
                "Archive Student",
                "Restore Student", 
                "Delete Student",
                "View Archived Students");

            int choice = InputValidator.readMenuChoice(input, 0, 8);

            switch (choice) {
                case 1 -> viewAllStudents();
                case 2 -> searchStudent();
                case 3 -> addStudent();
                case 4 -> updateStudent();
                case 5 -> archiveStudent();
                case 6 -> restoreStudent();
                case 7 -> deleteStudent();
                case 8 -> viewArchivedStudents();
                case 0 -> back = true;
                default -> MessagePrinter.error("Invalid menu option.");
            }
        }
    }

    // ==========================================================
    // View All Students
    // ==========================================================

    private void viewAllStudents() {

        List<Student> students = controller.getAllStudents();
        displayStudents(students);

    }

    // ==========================================================
    // Search Student
    // ==========================================================

    private void searchStudent() {

        System.out.print("Enter student number or name: ");
        String keyword = input.nextLine().trim();

        if (keyword.isBlank()) {
            System.out.println("Search keyword cannot be empty.");
            return;
        }

        List<Student> students = controller.searchStudents(keyword);
        displayStudents(students);

    }

    // ==========================================================
    // Add Student
    // ==========================================================

    private void addStudent() {

        Student student = new Student();

        System.out.print("Student Number: ");
        student.setStudentNumber(input.nextLine().trim());

        System.out.print("First Name: ");
        student.setFirstName(input.nextLine().trim());

        System.out.print("Last Name: ");
        student.setLastName(input.nextLine().trim());

        System.out.print("Email: ");
        student.setEmail(input.nextLine().trim());

        // Display available departments
        List<Department> departments = departmentController.getAllDepartments();

        if (departments.isEmpty()) {
            System.out.println("No departments found.");
            return;
        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-30s%n",
                "ID",
                "Department");

        TableFormatter.printDivider();

        for (Department department : departments) {
            System.out.printf(
                    "%-5d %-30s%n",
                    department.getId(),
                    department.getDepartmentName());
        }

        TableFormatter.printDivider();
        TableFormatter.printTotalRecords(departments.size());

        System.out.print("Department ID: ");
        student.setDepartmentId(readInt());

        // Every newly-created student starts as ACTIVE
        student.setStatus(Status.ACTIVE);

        if (controller.createStudent(student)) {
            System.out.println("Student created successfully.");
        } else {
            System.out.println("Failed to create student.");
        }
    }

    // ==========================================================
    // Update Student
    // ==========================================================

    private void updateStudent() {

        viewAllStudents();

        System.out.print("\nEnter Student ID to update: ");
        int id = readInt();

        Student student = controller.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Student Number (" + student.getStudentNumber() + "): ");
        String value = input.nextLine().trim();
        if (!value.isBlank()) {
            student.setStudentNumber(value);
        }

        System.out.print("First Name (" + student.getFirstName() + "): ");
        value = input.nextLine().trim();
        if (!value.isBlank()) {
            student.setFirstName(value);
        }

        System.out.print("Last Name (" + student.getLastName() + "): ");
        value = input.nextLine().trim();
        if (!value.isBlank()) {
            student.setLastName(value);
        }

        System.out.print("Email (" + student.getEmail() + "): ");
        value = input.nextLine().trim();
        if (!value.isBlank()) {
            student.setEmail(value);
        }

        if (controller.updateStudent(student)) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Failed to update student.");
        }

    }

    // ==========================================================
    // Archive Student
    // ==========================================================

    private void archiveStudent() {

        viewAllStudents();

        System.out.print("\nEnter Student ID to archive: ");
        int id = readInt();

        if (controller.archiveStudent(id)) {
            System.out.println("Student archived successfully.");
        } else {
            System.out.println("Failed to archive student.");
        }

    }

    // ==========================================================
    // Restore Student
    // ==========================================================

    private void restoreStudent() {

        viewArchivedStudents();

        System.out.print("\nEnter Student ID to restore: ");
        int id = readInt();

        if (controller.restoreStudent(id)) {
            System.out.println("Student restored successfully.");
        } else {
            System.out.println("Failed to restore student.");
        }

    }

    // ==========================================================
    // Delete Student
    // ==========================================================

    private void deleteStudent() {

        viewArchivedStudents();

        System.out.print("\nEnter Student ID to permanently delete: ");
        int id = readInt();

        if (controller.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Failed to delete student.");
        }

    }

    // ==========================================================
    // View Archived Students
    // ==========================================================

    private void viewArchivedStudents() {

        List<Student> students = controller.getArchivedStudents();
        displayStudents(students);

    }

    // ==========================================================
    // Display Helper
    // ==========================================================

    private void displayStudents(List<Student> students) {

        if (students.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-15s %-20s %-20s %-30s %-12s%n",
                "ID",
                "Student No.",
                "First Name",
                "Last Name",
                "Email",
                "Status");

        TableFormatter.printDivider();

        for (Student student : students) {

            System.out.printf(
                    "%-5d %-15s %-20s %-20s %-30s %-12s%n",
                    student.getId(),
                    student.getStudentNumber(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getStatus().getDisplayName());

        }

        TableFormatter.printTotalRecords(students.size());

    }

    // ==========================================================
    // Helper Methods
    // ==========================================================

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
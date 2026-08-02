package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.EnrollmentController;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.MenuPrinter;
import com.joysistvi.univenrollmentapp.utils.MessagePrinter;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

// View Class
// Handles the user interface for enrollment management
public class EnrollmentView {

    // Scanner object for user input
    private final Scanner input;

    // Controller for enrollment operations
    private final EnrollmentController controller;

    // Constructor
    public EnrollmentView(
            Scanner input, 
            EnrollmentController controller) {

        this.input = input;
        this.controller = controller;

    }

    // Displays the main menu for enrollment management
    public void displayMenu() {

        boolean back = false;

        while (!back) {

            MenuPrinter.printMenu(
                    "Enrollment Management",
                    "Back",
                    "View All Enrollments",
                    "Search Enrollments");
            
            int choice = InputValidator.readMenuChoice(input, 0, 2);

            switch (choice) {
                case 1 -> displayAllEnrollments();
                case 2 -> searchEnrollments();
                case 0 -> back = true;
                default -> MessagePrinter.error("Invalid menu option.");
            }
        }
    }

    // Display all enrollments
    private void displayAllEnrollments() {
        printEnrollments(controller.getAllEnrollments());
    }

    // Search enrollments
    private void searchEnrollments() {

        System.out.print("Search by Student, Course, School Year, or Semester: ");
        String keyword = input.nextLine().trim();

        if (keyword.isBlank()) {
            System.out.println("Search keyword cannot be empty.");
            return;
        }

        printEnrollments(controller.searchEnrollments(keyword));

    }

    // Print enrollment table
    private void printEnrollments(List<Enrollment> enrollments) {

        if (enrollments.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-15s %-25s %-12s %-30s %-15s %-12s %-15s%n",
                "ID",
                "Student No.",
                "Student Name",
                "Course",
                "Course Name",
                "School Year",
                "Semester",
                "Date Enrolled");

        TableFormatter.printDivider();

        for (Enrollment enrollment : enrollments) {

            System.out.printf(
                    "%-5d %-15s %-25s %-12s %-30s %-15s %-12s %-15s%n",
                    enrollment.getId(),
                    enrollment.getStudentNumber(),
                    enrollment.getStudentName(),
                    enrollment.getCourseCode(),
                    enrollment.getCourseName(),
                    enrollment.getSchoolYear(),
                    enrollment.getSemester().getDisplayName(),
                    enrollment.getDateEnrolled());

        }

        TableFormatter.printDivider();
        TableFormatter.printTotalRecords(enrollments.size());

    }
}
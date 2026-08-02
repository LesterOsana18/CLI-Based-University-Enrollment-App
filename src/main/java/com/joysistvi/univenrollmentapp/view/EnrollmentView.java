package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.EnrollmentController;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

// View Class
// Handles the user interface for enrollment management
public class EnrollmentView {

    private Scanner input;
    private final EnrollmentController controller;

    public EnrollmentView(EnrollmentController controller) {
        this.controller = controller;
    }

    public void displayMenu(Scanner input) {

        this.input = input;

        System.out.println("===== Enrollment Management =====");
        System.out.println("1. View All Enrollments");
        System.out.println("2. Search Enrollments");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        switch (readInt()) {
            case 1 -> displayAllEnrollments();
            case 2 -> searchEnrollments();
            case 0 -> {
            }
            default -> System.out.println("Invalid menu option.");
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
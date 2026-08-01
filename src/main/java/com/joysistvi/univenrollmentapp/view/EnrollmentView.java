package com.joysistvi.univenrollmentapp.view;

import com.joysistvi.univenrollmentapp.controller.EnrollmentController;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;
import java.util.List;
import java.util.Scanner;

public class EnrollmentView {
    private Scanner input;
    private final EnrollmentController controller;

    public EnrollmentView(EnrollmentController controller) {
        this.controller = controller;
    }

    public void displayMenu(Scanner input) {
        this.input = input;
        System.out.println("===== Enrollment Directory =====");
        System.out.println("1. View All Enrollments");
        System.out.println("2. Search Enrollments");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        switch (readInt()) {
            case 1 -> displayEnrollments(controller.getAllEnrollments(), "enrollments");
            case 2 -> searchEnrollments();
            case 0 -> { }
            default -> System.out.println("Invalid menu option.");
        }
    }

    private void searchEnrollments() {
        System.out.print("Search by student, course, school year, or semester: ");
        String keyword = input.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("Please enter a search term.");
            return;
        }
        displayEnrollments(controller.searchEnrollments(keyword), "matching enrollments");
    }

    private void displayEnrollments(List<Enrollment> enrollments, String label) {
        if (enrollments.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }
        printDivider();
        System.out.printf("%-5s %-15s %-22s %-12s %-28s %-14s %-16s %-12s%n",
                "ID", "Student No.", "Student", "Course", "Course Name", "School Year", "Semester", "Enrolled");
        printDivider();
        for (Enrollment enrollment : enrollments) {
            System.out.printf("%-5d %-15s %-22s %-12s %-28s %-14s %-16s %-12s%n",
                    enrollment.getId(), enrollment.getStudentNumber(), enrollment.getStudentName(),
                    enrollment.getCourseCode(), enrollment.getCourseName(), enrollment.getSchoolYear(),
                    enrollment.getSemester().getDisplayName(), enrollment.getDateEnrolled());
        }
        TableFormatter.printTotalRecords(enrollments.size());
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
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }
}

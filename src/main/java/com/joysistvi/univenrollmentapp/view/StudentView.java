package com.joysistvi.univenrollmentapp.view;

import com.joysistvi.univenrollmentapp.controller.StudentController;
import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private Scanner input;
    private final StudentController controller = new StudentController();

    public void displayMenu(Scanner input) {
        this.input = input;
        System.out.println("===== Student Directory =====");
        System.out.println("1. View All Students");
        System.out.println("2. Search Students");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        switch (readInt()) {
            case 1 -> displayStudents(controller.getAllStudents(), "students");
            case 2 -> searchStudents();
            case 0 -> { }
            default -> System.out.println("Invalid menu option.");
        }
    }

    private void searchStudents() {
        System.out.print("Search by student number, name, email, or department: ");
        String keyword = input.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("Please enter a search term.");
            return;
        }
        displayStudents(controller.searchStudents(keyword), "matching students");
    }

    private void displayStudents(List<Student> students, String label) {
        if (students.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }
        printDivider();
        System.out.printf("%-5s %-15s %-22s %-28s %-28s %-12s%n",
                "ID", "Student No.", "Name", "Email", "Department", "Status");
        printDivider();
        for (Student student : students) {
            System.out.printf("%-5d %-15s %-22s %-28s %-28s %-12s%n",
                    student.getId(), student.getStudentNumber(),
                    student.getFirstName() + " " + student.getLastName(),
                    student.getEmail(), student.getDepartmentName(), student.getStatus().getDisplayName());
        }
        TableFormatter.printTotalRecords(students.size());
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

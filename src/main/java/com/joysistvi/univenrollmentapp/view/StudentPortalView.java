package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.StudentPortalController;
import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

public class StudentPortalView {

    // Scanner for user input
    private Scanner input;

    // Controller for handling student portal operations
    private final StudentPortalController controller;

    // Constructor
    public StudentPortalView(StudentPortalController controller) {
        this.controller = controller;
    }

    // Display the main menu for the student portal
    public void displayMenu(Scanner input, int userId) {

        this.input = input;

        while (true) {

            Student student = controller.getStudentByUserId(userId);

            if (student == null) {
                System.out.println("No student profile is linked to this account.");
                return;
            }

            System.out.println("\n===== Student Portal =====");
            System.out.println("1. View Student Information");
            System.out.println("2. View Available Courses");
            System.out.println("3. View Enrollment History");
            System.out.println("4. Enroll in a Course");
            System.out.println("5. Drop Enrolled Course");
            System.out.println("6. View Prerequisites");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            switch (readInt()) {
                case 1 -> showStudentInformation(student);
                case 2 -> showAvailableCourses();
                case 3 -> showEnrollmentHistory(student);
                case 4 -> enrollInCourse(student);
                case 5 -> dropEnrollment(student);
                case 6 -> showPrerequisites();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    // ==========================================================
    // Student Information
    // ==========================================================

    private void showStudentInformation(Student student) {

        TableFormatter.printDivider();

        System.out.println("Student Information");

        TableFormatter.printDivider();

        System.out.println("Student Number : " + student.getStudentNumber());
        System.out.println("Name           : " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Email          : " + student.getEmail());
        System.out.println("Status         : " + student.getStatus().getDisplayName());

        TableFormatter.printDivider();
    }

    // ==========================================================
    // Available Courses
    // ==========================================================

    private void showAvailableCourses() {

        List<Course> courses = controller.getAllCourses();

        if (courses.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-12s %-35s %-7s %-25s%n",
                "ID",
                "Code",
                "Course Name",
                "Units",
                "Department");

        TableFormatter.printDivider();

        for (Course course : courses) {

            System.out.printf(
                    "%-5d %-12s %-35s %-7d %-25s%n",
                    course.getId(),
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getUnits(),
                    course.getDepartmentName());

        }

        TableFormatter.printDivider();
        TableFormatter.printTotalRecords(courses.size());
    }

    // ==========================================================
    // Enrollment History
    // ==========================================================

    private void showEnrollmentHistory(Student student) {

        List<Enrollment> enrollments =
                controller.getEnrollmentHistory(student.getId());

        if (enrollments.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-12s %-30s %-15s %-12s %-15s%n",
                "ID",
                "Course",
                "Course Name",
                "School Year",
                "Semester",
                "Enrolled");

        TableFormatter.printDivider();

        for (Enrollment enrollment : enrollments) {

            System.out.printf(
                    "%-5d %-12s %-30s %-15s %-12s %-15s%n",
                    enrollment.getId(),
                    enrollment.getCourseCode(),
                    enrollment.getCourseName(),
                    enrollment.getSchoolYear(),
                    enrollment.getSemester().getDisplayName(),
                    enrollment.getDateEnrolled());

        }

        TableFormatter.printDivider();
        TableFormatter.printTotalRecords(enrollments.size());
    }

    // ==========================================================
    // Enroll
    // ==========================================================

    private void enrollInCourse(Student student) {

        showAvailableCourses();

        System.out.print("\nEnter Course ID: ");
        int courseId = readInt();

        System.out.print("Enter School Year (e.g. 2026-2027): ");
        String schoolYear = input.nextLine().trim();

        Semester semester = readSemester();

        String result = controller.enrollStudent(
                student.getId(),
                courseId,
                schoolYear,
                semester);

        System.out.println(result);
        System.out.println();
    }

    // ==========================================================
    // Drop Enrollment
    // ==========================================================

    private void dropEnrollment(Student student) {

        List<Enrollment> enrollments =
                controller.getEnrollmentHistory(student.getId());

        if (enrollments.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-12s %-30s %-15s %-12s%n",
                "ID",
                "Course",
                "Course Name",
                "School Year",
                "Semester");

        TableFormatter.printDivider();

        for (Enrollment enrollment : enrollments) {

            System.out.printf(
                    "%-5d %-12s %-30s %-15s %-12s%n",
                    enrollment.getId(),
                    enrollment.getCourseCode(),
                    enrollment.getCourseName(),
                    enrollment.getSchoolYear(),
                    enrollment.getSemester().getDisplayName());

        }

        TableFormatter.printDivider();

        System.out.print("Enter Enrollment ID to drop: ");
        int enrollmentId = readInt();

        if (controller.dropEnrollment(enrollmentId, student.getId())) {
            System.out.println("Enrollment dropped successfully.");
        } else {
            System.out.println("Failed to drop enrollment.");
        }

        System.out.println();
    }

    // ==========================================================
    // Prerequisites
    // ==========================================================

    private void showPrerequisites() {

        List<Prerequisite> prerequisites =
                controller.getAllPrerequisites();

        if (prerequisites.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-15s %-30s %-15s %-30s%n",
                "ID",
                "Course",
                "Course Name",
                "Prerequisite",
                "Prerequisite Name");

        TableFormatter.printDivider();

        for (Prerequisite prerequisite : prerequisites) {

            System.out.printf(
                    "%-5d %-15s %-30s %-15s %-30s%n",
                    prerequisite.getId(),
                    prerequisite.getCourseCode(),
                    prerequisite.getCourseName(),
                    prerequisite.getPrerequisiteCourseCode(),
                    prerequisite.getPrerequisiteCourseName());

        }

        TableFormatter.printDivider();
        TableFormatter.printTotalRecords(prerequisites.size());
    }

    // ==========================================================
    // Helpers
    // ==========================================================

    private Semester readSemester() {

        System.out.println("\nSelect Semester:");
        System.out.println("1. First");
        System.out.println("2. Second");
        System.out.println("3. Summer");
        System.out.print("Choice: ");

        return switch (readInt()) {
            case 1 -> Semester.FIRST;
            case 2 -> Semester.SECOND;
            case 3 -> Semester.SUMMER;
            default -> {
                System.out.println("Invalid semester.");
                yield Semester.FIRST;
            }
        };
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
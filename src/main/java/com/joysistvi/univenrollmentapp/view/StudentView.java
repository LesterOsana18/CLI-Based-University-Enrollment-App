package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.StudentController;
import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

public class StudentView {

    private Scanner input;
    private final StudentController controller;

    public StudentView(StudentController controller) {
        this.controller = controller;
    }

    public void displayMenu(Scanner input, int userId) {
        this.input = input;

        Student student = controller.getStudentByUserId(userId);

        if (student == null) {
            System.out.println("No student profile is linked to this account.");
            return;
        }

        while (true) {

            System.out.println("\n===== Student Menu =====");
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

    private void showStudentInformation(Student student) {

        printDivider();
        System.out.println("Student Information");
        printDivider();

        System.out.println("Student Number : " + student.getStudentNumber());
        System.out.println("Name           : " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Email          : " + student.getEmail());
        System.out.println("Status         : " + student.getStatus());

    }

    private void showAvailableCourses() {

        List<Course> courses = controller.getAllCourses();

        if (courses.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        printDivider();
        System.out.printf("%-5s %-12s %-35s %-7s %-25s%n",
                "ID", "Code", "Course Name", "Units", "Department");
        printDivider();

        for (Course course : courses) {
            System.out.printf("%-5d %-12s %-35s %-7d %-25s%n",
                    course.getId(),
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getUnits(),
                    course.getDepartmentName());
        }

        TableFormatter.printTotalRecords(courses.size());
    }

    private void showEnrollmentHistory(Student student) {

        List<Enrollment> enrollments =
                controller.getEnrollmentHistory(student.getId());

        if (enrollments.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        printDivider();
        System.out.printf("%-5s %-12s %-18s %-12s %-15s %-15s%n",
                "ID",
                "Course ID",
                "School Year",
                "Semester",
                "Enrolled",
                "Status");
        printDivider();

        for (Enrollment enrollment : enrollments) {

            System.out.printf("%-5d %-12d %-18s %-12s %-15s %-15s%n",
                    enrollment.getId(),
                    enrollment.getCourseId(),
                    enrollment.getSchoolYear(),
                    enrollment.getSemester().getDisplayName(),
                    enrollment.getDateEnrolled(),
                    "ACTIVE");
        }

        TableFormatter.printTotalRecords(enrollments.size());
    }

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
    }

        private void dropEnrollment(Student student) {

        List<Enrollment> enrollments =
                controller.getEnrollmentHistory(student.getId());

        if (enrollments.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        printDivider();
        System.out.printf("%-5s %-12s %-18s %-12s%n",
                "ID",
                "Course ID",
                "School Year",
                "Semester");
        printDivider();

        for (Enrollment enrollment : enrollments) {
            System.out.printf("%-5d %-12d %-18s %-12s%n",
                    enrollment.getId(),
                    enrollment.getCourseId(),
                    enrollment.getSchoolYear(),
                    enrollment.getSemester().getDisplayName());
        }

        System.out.print("\nEnter Enrollment ID to drop: ");
        int enrollmentId = readInt();

        if (controller.dropEnrollment(enrollmentId, student.getId())) {
            System.out.println("Enrollment dropped successfully.");
        } else {
            System.out.println("Failed to drop enrollment.");
        }
    }

    private void showPrerequisites() {

        List<Prerequisite> prerequisites =
                controller.getAllPrerequisites();

        if (prerequisites.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        printDivider();
        System.out.printf("%-5s %-15s %-30s %-15s %-30s%n",
                "ID",
                "Course",
                "Course Name",
                "Prerequisite",
                "Prerequisite Name");
        printDivider();

        for (Prerequisite prerequisite : prerequisites) {

            System.out.printf("%-5d %-15s %-30s %-15s %-30s%n",
                    prerequisite.getId(),
                    prerequisite.getCourseCode(),
                    prerequisite.getCourseName(),
                    prerequisite.getPrerequisiteCourseCode(),
                    prerequisite.getPrerequisiteCourseName());

        }

        TableFormatter.printTotalRecords(prerequisites.size());
    }

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
                System.out.println("Invalid semester. Defaulting to First Semester.");
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

    private void printDivider() {
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

}
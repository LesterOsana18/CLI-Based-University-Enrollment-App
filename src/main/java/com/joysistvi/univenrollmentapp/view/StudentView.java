package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.StudentController;
import com.joysistvi.univenrollmentapp.enums.Semester;
import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.model.Enrollment;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.model.Student;
import com.joysistvi.univenrollmentapp.utils.ConsoleUtils;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

// View Class
// Displays the Student module menu and handles console interaction
public class StudentView {

    private final Scanner input;
    private final StudentController studentController;

    // Constructor
    public StudentView(Scanner input, StudentController studentController) {
        this.input = input;
        this.studentController = studentController;
    }

    // Entry point called by MainMenuView for a logged-in student
    public void run(int userId) {

        Student student = studentController.getStudentByUserId(userId);

        if (student == null) {

            ConsoleUtils.printError(
                    "No student profile is linked to this account. Please contact the registrar.");

            ConsoleUtils.pressEnterToContinue(input);
            return;

        }

        boolean back = false;

        while (!back) {

            ConsoleUtils.printHeader("Student Menu");
            System.out.println("1. Student Dashboard");
            System.out.println("2. View Available Courses");
            System.out.println("3. View Enrollment History");
            System.out.println("4. Enroll in a Course");
            System.out.println("5. Drop an Enrolled Course");
            System.out.println("6. View Prerequisite Information");
            System.out.println("0. Back");

            int choice = InputValidator.readMenuChoice(input);

            switch (choice) {

                case 1:
                    showDashboard(student);
                    break;

                case 2:
                    showAvailableCourses();
                    break;

                case 3:
                    showEnrollmentHistory(student);
                    break;

                case 4:
                    enrollInCourse(student);
                    break;

                case 5:
                    dropEnrolledCourse(student);
                    break;

                case 6:
                    showPrerequisites();
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    ConsoleUtils.printError("Invalid menu option.");

            }

        }

    }

    // ==========================================================
    // 1. STUDENT DASHBOARD / INFORMATION
    // ==========================================================

    private void showDashboard(Student student) {

        ConsoleUtils.printHeader("Student Information");

        System.out.println("Student Number : " + student.getStudentNumber());
        System.out.println("Name           : " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Email          : " + student.getEmail());
        System.out.println("Status         : " + student.getStatus());

        ConsoleUtils.pressEnterToContinue(input);

    }

    // ==========================================================
    // 2. VIEW AVAILABLE COURSES
    // ==========================================================

    private void showAvailableCourses() {

        ConsoleUtils.printHeader("Available Courses");

        List<Course> courses = studentController.listAvailableCourses();

        if (courses.isEmpty()) {

            TableFormatter.printNoRecordsFound("courses");

        } else {

            for (Course course : courses) {

                System.out.println(
                        course.getCourseCode() + " - " + course.getCourseName()
                        + " (" + course.getUnits() + " units)"
                        + " [" + course.getDepartmentName() + "]");

            }

            TableFormatter.printDivider();
            TableFormatter.printTotalRecords("courses", courses.size());

        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // ==========================================================
    // 3. VIEW ENROLLMENT HISTORY
    // ==========================================================

    private void showEnrollmentHistory(Student student) {

        ConsoleUtils.printHeader("Enrollment History");

        List<Enrollment> history =
                studentController.listEnrollmentHistory(student.getId());

        if (history.isEmpty()) {

            TableFormatter.printNoRecordsFound("enrollment records");

        } else {

            for (Enrollment enrollment : history) {

                System.out.println(
                        "Enrollment ID: " + enrollment.getId()
                        + " | Course ID: " + enrollment.getCourseId()
                        + " | S.Y. " + enrollment.getSchoolYear()
                        + " | " + enrollment.getSemester()
                        + " | Enrolled on: " + enrollment.getDateEnrolled());

            }

            TableFormatter.printDivider();
            TableFormatter.printTotalRecords("enrollment records", history.size());

        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // ==========================================================
    // 4. ENROLL IN A COURSE
    // ==========================================================

    private void enrollInCourse(Student student) {

        ConsoleUtils.printHeader("Enroll in a Course");

        showAvailableCoursesInline();

        int courseId = InputValidator.readPositiveInt(input, "Course ID");
        String schoolYear = InputValidator.readRequiredString(input, "School Year (e.g. 2025-2026)");
        Semester semester = readSemester();

        String result = studentController.enroll(
                student.getId(), courseId, schoolYear, semester);

        if (result.equals("Enrollment successful.")) {
            ConsoleUtils.printSuccess(result);
        } else {
            ConsoleUtils.printError(result);
        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // ==========================================================
    // 5. DROP AN ENROLLED COURSE
    // ==========================================================

    private void dropEnrolledCourse(Student student) {

        ConsoleUtils.printHeader("Drop an Enrolled Course");

        List<Enrollment> history =
                studentController.listEnrollmentHistory(student.getId());

        if (history.isEmpty()) {

            TableFormatter.printNoRecordsFound("enrollment records");
            ConsoleUtils.pressEnterToContinue(input);
            return;

        }

        for (Enrollment enrollment : history) {

            System.out.println(
                    "Enrollment ID: " + enrollment.getId()
                    + " | Course ID: " + enrollment.getCourseId()
                    + " | S.Y. " + enrollment.getSchoolYear()
                    + " | " + enrollment.getSemester());

        }

        int enrollmentId = InputValidator.readPositiveInt(input, "Enrollment ID to drop");

        boolean confirmed = InputValidator.confirmAction(
                input, "\nAre you sure you want to drop this enrollment?");

        if (!confirmed) {

            ConsoleUtils.printWarning("Drop cancelled.");
            ConsoleUtils.pressEnterToContinue(input);
            return;

        }

        boolean dropped = studentController.drop(enrollmentId, student.getId());

        if (dropped) {
            ConsoleUtils.printSuccess("Enrollment dropped successfully.");
        } else {
            ConsoleUtils.printError("Could not drop that enrollment. Please check the Enrollment ID.");
        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // ==========================================================
    // 6. VIEW PREREQUISITE INFORMATION
    // ==========================================================

    private void showPrerequisites() {

        ConsoleUtils.printHeader("Prerequisite Information");

        List<Prerequisite> prerequisites = studentController.listAllPrerequisites();

        if (prerequisites.isEmpty()) {

            TableFormatter.printNoRecordsFound("prerequisite records");

        } else {

            for (Prerequisite prerequisite : prerequisites) {

                String course = prerequisite.getCourseCode() != null
                        ? prerequisite.getCourseCode()
                        : ("Course ID " + prerequisite.getCourseId());

                String prereq = prerequisite.getPrerequisiteCourseCode() != null
                        ? prerequisite.getPrerequisiteCourseCode()
                        : ("Course ID " + prerequisite.getPrerequisiteCourseId());

                System.out.println(course + "  requires  " + prereq);

            }

            TableFormatter.printDivider();
            TableFormatter.printTotalRecords("prerequisite records", prerequisites.size());

        }

        ConsoleUtils.pressEnterToContinue(input);

    }

    // ==========================================================
    // HELPERS
    // ==========================================================

    private void showAvailableCoursesInline() {

        List<Course> courses = studentController.listAvailableCourses();

        for (Course course : courses) {

            System.out.println(
                    "  [" + course.getId() + "] " + course.getCourseCode()
                    + " - " + course.getCourseName()
                    + " (" + course.getUnits() + " units)");

        }

        System.out.println();

    }

    private Semester readSemester() {

        while (true) {

            System.out.println("Semester: 1) 1st  2) 2nd  3) Summer");
            int choice = InputValidator.readMenuChoice(input);

            switch (choice) {
                case 1: return Semester.FIRST;
                case 2: return Semester.SECOND;
                case 3: return Semester.SUMMER;
                default: ConsoleUtils.printError("Please choose 1, 2, or 3.");
            }

        }

    }
}
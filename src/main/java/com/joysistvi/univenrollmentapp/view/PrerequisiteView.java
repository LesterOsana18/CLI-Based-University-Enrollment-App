package com.joysistvi.univenrollmentapp.view;

import java.util.List;
import java.util.Scanner;

import com.joysistvi.univenrollmentapp.controller.CourseController;
import com.joysistvi.univenrollmentapp.controller.PrerequisiteController;
import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.utils.InputValidator;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

public class PrerequisiteView {

    private Scanner input;

    private final PrerequisiteController prerequisiteController;
    private final CourseController courseController;

    public PrerequisiteView(
            PrerequisiteController prerequisiteController,
            CourseController courseController) {

        this.prerequisiteController = prerequisiteController;
        this.courseController = courseController;

    }

    public void displayMenu(Scanner input) {

        this.input = input;

        while (true) {

            System.out.println("\n===== Prerequisite Management =====");
            System.out.println("1. View All Prerequisites");
            System.out.println("2. Add Prerequisite");
            System.out.println("3. Update Prerequisite");
            System.out.println("4. Delete Prerequisite");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            switch (InputValidator.readPositiveInt(input, "Selected menu option")) {

                case 1 -> displayAllPrerequisites();
                case 2 -> createPrerequisite();
                case 3 -> updatePrerequisite();
                case 4 -> deletePrerequisite();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid menu option.");

            }

        }

    }

    private void displayAllPrerequisites() {

        List<Prerequisite> prerequisites =
                prerequisiteController.getAllPrerequisites();

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

        TableFormatter.printTotalRecords(prerequisites.size());

    }

    private void createPrerequisite() {

        CoursePair pair = readCoursePair();

        if (pair == null) {
            return;
        }

        if (prerequisiteController.createPrerequisite(
                pair.courseId(),
                pair.prerequisiteCourseId())) {

            System.out.println("Prerequisite added successfully.");

        } else {

            System.out.println("Failed to add prerequisite.");

        }

    }

    private void updatePrerequisite() {

        displayAllPrerequisites();

        System.out.print("Enter Prerequisite ID to update: ");
        int id = InputValidator.readPositiveInt(input, "Prerequisite ID");

        CoursePair pair = readCoursePair();

        if (pair == null) {
            return;
        }

        if (prerequisiteController.updatePrerequisite(
                id,
                pair.courseId(),
                pair.prerequisiteCourseId())) {

            System.out.println("Prerequisite updated successfully.");

        } else {

            System.out.println("Failed to update prerequisite.");

        }

    }

    private void deletePrerequisite() {

        displayAllPrerequisites();

        System.out.print("Enter Prerequisite ID to delete: ");
        int id = InputValidator.readPositiveInt(input, "Prerequisite ID");

        if (prerequisiteController.deletePrerequisite(id)) {

            System.out.println("Prerequisite deleted successfully.");

        } else {

            System.out.println("Failed to delete prerequisite.");

        }

    }

    private CoursePair readCoursePair() {

        List<Course> courses = courseController.getAllCourses();

        if (courses.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return null;
        }

        printCourses(courses);

        System.out.print("Enter Course ID: ");
        int courseId = InputValidator.readPositiveInt(input, "Course ID");

        System.out.print("Enter Prerequisite Course ID: ");
        int prerequisiteCourseId = InputValidator.readPositiveInt(input, "Prerequisite Course ID");

        if (!containsCourse(courses, courseId)
                || !containsCourse(courses, prerequisiteCourseId)) {

            System.out.println("Invalid course selection.");
            return null;

        }

        if (courseId == prerequisiteCourseId) {

            System.out.println("A course cannot be its own prerequisite.");
            return null;

        }

        return new CoursePair(courseId, prerequisiteCourseId);

    }

    private void printCourses(List<Course> courses) {

        TableFormatter.printDivider();

        System.out.printf(
                "%-5s %-15s %-35s%n",
                "ID",
                "Code",
                "Course Name");

        TableFormatter.printDivider();

        for (Course course : courses) {

            System.out.printf(
                    "%-5d %-15s %-35s%n",
                    course.getId(),
                    course.getCourseCode(),
                    course.getCourseName());

        }

        TableFormatter.printTotalRecords(courses.size());

    }

    private boolean containsCourse(List<Course> courses, int id) {

        return courses.stream()
                .anyMatch(course -> course.getId() == id);

    }

    private record CoursePair(
            int courseId,
            int prerequisiteCourseId) {
    }

}
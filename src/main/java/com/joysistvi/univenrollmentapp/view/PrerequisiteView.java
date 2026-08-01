package com.joysistvi.univenrollmentapp.view;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.controller.CourseController;
import com.joysistvi.univenrollmentapp.controller.PrerequisiteController;
import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.model.Prerequisite;
import com.joysistvi.univenrollmentapp.repository.CourseRepositoryImpl;
import com.joysistvi.univenrollmentapp.repository.PrerequisiteRepositoryImpl;
import com.joysistvi.univenrollmentapp.service.CourseServiceImpl;
import com.joysistvi.univenrollmentapp.service.PrerequisiteServiceImpl;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

import java.util.List;
import java.util.Scanner;

public class PrerequisiteView {

    private Scanner input;

    private final PrerequisiteController controller;
    private final CourseController courseController;

    public PrerequisiteView() {
        this(
                new PrerequisiteController(
                        new PrerequisiteServiceImpl(
                                new PrerequisiteRepositoryImpl(new DbConnection()))),
                new CourseController(
                        new CourseServiceImpl(
                                new CourseRepositoryImpl(new DbConnection()))));
    }

    public PrerequisiteView(
            PrerequisiteController controller,
            CourseController courseController) {

        this.controller = controller;
        this.courseController = courseController;

    }

    public void displayMenu(Scanner input) {

        this.input = input;

        System.out.println("===== Prerequisite Management =====");
        System.out.println("1. View All Prerequisites");
        System.out.println("2. Add Prerequisite");
        System.out.println("3. Update Prerequisite");
        System.out.println("4. Delete Prerequisite");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        switch (readInt()) {

            case 1 -> displayAllPrerequisites();
            case 2 -> createPrerequisite();
            case 3 -> updatePrerequisite();
            case 4 -> deletePrerequisite();
            case 0 -> {
            }
            default -> System.out.println("Invalid menu option.");

        }

    }

    private void displayAllPrerequisites() {

        List<Prerequisite> prerequisites = controller.getAllPrerequisites();

        if (prerequisites.isEmpty()) {
            TableFormatter.printNoRecordsFound();
            return;
        }

        printDivider();

        System.out.printf(
                "%-5s %-15s %-30s %-15s %-30s%n",
                "ID",
                "Course",
                "Course Name",
                "Prerequisite",
                "Prerequisite Name");

        printDivider();

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

        if (controller.createPrerequisite(
                pair.courseId(),
                pair.prerequisiteCourseId())) {

            System.out.println("Prerequisite added successfully.");

        } else {

            System.out.println("Failed to add prerequisite.");

        }

    }

    private void updatePrerequisite() {

        displayAllPrerequisites();

        System.out.print("Enter prerequisite ID to update: ");
        int id = readInt();

        CoursePair pair = readCoursePair();

        if (pair == null) {
            return;
        }

        if (controller.updatePrerequisite(
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

        System.out.print("Enter prerequisite ID to delete: ");
        int id = readInt();

        if (controller.deletePrerequisite(id)) {

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
        int courseId = readInt();

        System.out.print("Enter Prerequisite Course ID: ");
        int prerequisiteCourseId = readInt();

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

        printDivider();

        System.out.printf(
                "%-5s %-15s %-35s%n",
                "ID",
                "Code",
                "Course Name");

        printDivider();

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

        System.out.println("--------------------------------------------------------------------------------------------------------------");

    }

    private record CoursePair(
            int courseId,
            int prerequisiteCourseId) {
    }

}
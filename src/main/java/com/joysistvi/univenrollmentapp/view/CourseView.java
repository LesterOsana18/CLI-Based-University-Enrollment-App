package com.joysistvi.univenrollmentapp.view;

import com.joysistvi.univenrollmentapp.controller.CourseController;
import com.joysistvi.univenrollmentapp.controller.DepartmentController;
import com.joysistvi.univenrollmentapp.model.Course;
import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;
import java.util.List;
import java.util.Scanner;

public class CourseView {
    private Scanner input;
    private final CourseController controller = new CourseController();
    private final DepartmentController departmentController = new DepartmentController();

    public void displayMenu(Scanner input) {
        this.input = input;
        System.out.println("===== Course Management =====");
        System.out.println("1. View All Courses");
        System.out.println("2. Create Course");
        System.out.println("3. Update Course");
        System.out.println("4. Archive Course");
        System.out.println("5. View Archived Courses");
        System.out.println("6. Manage Prerequisites");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        switch (readInt()) {
            case 1 -> displayAllCourses();
            case 2 -> createCourse();
            case 3 -> updateCourse();
            case 4 -> deleteCourse();
            case 5 -> displayArchivedCourses();
            case 6 -> new PrerequisiteView().displayMenu(input);
            case 0 -> { }
            default -> System.out.println("Invalid menu option.");
        }
    }

    public void displayAllCourses() {
        printCourses(controller.getAllCourses(), "active courses");
    }

    public void displayArchivedCourses() {
        printCourses(controller.getArchivedCourses(), "archived courses");
    }

    private void createCourse() {
        CourseDetails details = readCourseDetails();
        if (details == null) return;

        if (controller.createCourse(details.courseCode(), details.courseName(), details.units(), details.departmentId())) {
            System.out.println("Course created successfully.");
        } else {
            System.out.println("Failed to create course. The course code may already exist or the department is invalid.");
        }
    }

    private void updateCourse() {
        displayAllCourses();
        System.out.print("Enter course ID to update: ");
        int id = readInt();
        CourseDetails details = readCourseDetails();
        if (details == null) return;

        if (controller.updateCourse(id, details.courseCode(), details.courseName(), details.units(), details.departmentId())) {
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Failed to update course. Please check the course ID and entered details.");
        }
    }

    private void deleteCourse() {
        displayAllCourses();
        System.out.print("Enter course ID to archive: ");
        int id = readInt();
        if (controller.deleteCourse(id)) {
            System.out.println("Course archived successfully.");
        } else {
            System.out.println("Failed to archive course. Please check the course ID and try again.");
        }
    }

    private CourseDetails readCourseDetails() {
        System.out.print("Enter course code: ");
        String courseCode = input.nextLine().trim().toUpperCase();
        System.out.print("Enter course name: ");
        String courseName = input.nextLine().trim();
        if (courseCode.isEmpty() || courseName.isEmpty()) {
            System.out.println("Course code and course name cannot be empty.");
            return null;
        }

        System.out.print("Enter units: ");
        int units = readInt();
        if (units < 1 || units > 255) {
            System.out.println("Units must be between 1 and 255.");
            return null;
        }

        printDepartments();
        System.out.print("Enter department ID: ");
        int departmentId = readInt();
        if (!isActiveDepartment(departmentId)) {
            System.out.println("Please select an active department ID.");
            return null;
        }
        return new CourseDetails(courseCode, courseName, units, departmentId);
    }

    private void printDepartments() {
        List<Department> departments = departmentController.getAllDepartments();
        if (departments.isEmpty()) {
            TableFormatter.printNoRecordsFound("active departments");
            return;
        }
        TableFormatter.printDivider();
        System.out.printf("%-5s %-30s%n", "ID", "Department Name");
        TableFormatter.printDivider();
        for (Department department : departments) {
            System.out.printf("%-5d %-30s%n", department.getId(), department.getDepartmentName());
        }
    }

    private boolean isActiveDepartment(int departmentId) {
        return departmentController.getAllDepartments().stream()
                .anyMatch(department -> department.getId() == departmentId);
    }

    private void printCourses(List<Course> courses, String label) {
        if (courses.isEmpty()) {
            TableFormatter.printNoRecordsFound(label);
            return;
        }
        TableFormatter.printDivider();
        System.out.printf("%-5s %-12s %-30s %-7s %-30s%n", "ID", "Code", "Course Name", "Units", "Department");
        TableFormatter.printDivider();
        for (Course course : courses) {
            System.out.printf("%-5d %-12s %-30s %-7d %-30s%n",
                    course.getId(), course.getCourseCode(), course.getCourseName(),
                    course.getUnits(), course.getDepartmentName());
        }
        TableFormatter.printTotalRecords(label, courses.size());
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

    private record CourseDetails(String courseCode, String courseName, int units, int departmentId) { }
}

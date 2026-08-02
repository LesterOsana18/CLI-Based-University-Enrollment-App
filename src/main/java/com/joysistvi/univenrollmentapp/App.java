package com.joysistvi.univenrollmentapp;

import java.util.Scanner;

import com.joysistvi.univenrollmentapp.config.DbConnection;
import com.joysistvi.univenrollmentapp.controller.*;
import com.joysistvi.univenrollmentapp.repository.*;
import com.joysistvi.univenrollmentapp.service.*;
import com.joysistvi.univenrollmentapp.view.*;

public final class App {

    private static final Scanner input = new Scanner(System.in);

    private App() {
    }

    public static void main(String[] args) {

        try {

            DbConnection dbConnection = new DbConnection();

            // ======================================================
            // Repositories
            // ======================================================

            UserRepository userRepository =
                    new UserRepositoryImpl(dbConnection);

            StudentRepository studentRepository =
                    new StudentRepositoryImpl(dbConnection);

            DepartmentRepository departmentRepository =
                    new DepartmentRepositoryImpl(dbConnection);

            CourseRepository courseRepository =
                    new CourseRepositoryImpl(dbConnection);

            PrerequisiteRepository prerequisiteRepository =
                    new PrerequisiteRepositoryImpl(dbConnection);

            EnrollmentRepository enrollmentRepository =
                    new EnrollmentRepositoryImpl(dbConnection);

            EmployeeRepository employeeRepository =
                    new EmployeeRepositoryImpl(dbConnection);

            // ======================================================
            // Services
            // ======================================================

            UserService userService =
                    new UserServiceImpl(userRepository);

            StudentService studentService =
                    new StudentServiceImpl(studentRepository);

            DepartmentService departmentService =
                    new DepartmentServiceImpl(departmentRepository);

            CourseService courseService =
                    new CourseServiceImpl(courseRepository);

            PrerequisiteService prerequisiteService =
                    new PrerequisiteServiceImpl(prerequisiteRepository);

            EnrollmentService enrollmentService =
                    new EnrollmentServiceImpl(
                            enrollmentRepository,
                            studentRepository,
                            prerequisiteService);

            EmployeeService employeeService =
                    new EmployeeServiceImpl(employeeRepository);

            // ======================================================
            // Controllers
            // ======================================================

            UserController userController =
                    new UserController(userService);

            DepartmentController departmentController =
                    new DepartmentController(departmentService);

            CourseController courseController =
                    new CourseController(courseService);

            PrerequisiteController prerequisiteController =
                    new PrerequisiteController(prerequisiteService);

            EnrollmentController enrollmentController =
                    new EnrollmentController(enrollmentService);

            EmployeeController employeeController =
                    new EmployeeController(employeeService);

            StudentPortalController studentPortalController =
                    new StudentPortalController(
                            studentService,
                            courseService,
                            enrollmentService,
                            prerequisiteService);

            StudentManagementController studentManagementController =
                    new StudentManagementController(studentService);

            // ======================================================
            // Views
            // ======================================================

            StudentPortalView studentPortalView =
                    new StudentPortalView(input, studentPortalController);

            StudentManagementView studentManagementView =
                    new StudentManagementView(input, studentManagementController, departmentController);

            CourseView courseView =
                    new CourseView(
                            input,
                            courseController,
                            departmentController,
                            prerequisiteController);

            DepartmentView departmentView =
                    new DepartmentView(
                            input,
                            departmentController);

            EnrollmentView enrollmentView =
                    new EnrollmentView(
                            input,
                            enrollmentController);

            EmployeeView employeeView =
                    new EmployeeView(
                            input,
                            employeeController);

            UserView userView =
                    new UserView(
                            input,
                            userController);

            PrerequisiteView prerequisiteView =
                    new PrerequisiteView(
                            input,
                            prerequisiteController,
                            courseController);

            MainMenuView mainMenuView =
                    new MainMenuView(
                            input,
                            studentPortalView,
                            studentManagementView,
                            courseView,
                            departmentView,
                            enrollmentView,
                            employeeView,
                            userView,
                            prerequisiteView);

            LoginView loginView =
                    new LoginView(
                            userController,
                            input);

            while (true) {

                if (!loginView.run()) {
                    break;
                }

                mainMenuView.displayMenu();

            }

            System.out.println("\nThank you for using the University Enrollment System!");

        } finally {

            input.close();

        }

    }

}
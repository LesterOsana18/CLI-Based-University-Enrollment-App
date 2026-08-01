package com.joysistvi.univenrollmentapp.view;


import com.joysistvi.univenrollmentapp.controller.DepartmentController;
import com.joysistvi.univenrollmentapp.model.Department;
import com.joysistvi.univenrollmentapp.utils.TableFormatter;

import java.util.List;
import java.util.Scanner;

public class DepartmentView {
   
    Scanner input;
    public void displayMenu(Scanner input){
        this.input = input;
        System.out.println("===== Department =====");
        System.out.println("1 . View All Department");
        System.out.print("Enter Choice: ");
        int choice = readMenuChoice();
        
        switch(choice){
            case 1 -> displayAllDepartments();
        }
        
    }
    
    public void displayAllDepartments(){
        List <Department> departments = new DepartmentController().getAllDepartments();
        TableFormatter.printBorder("-");
        for(Department department : departments){
            System.out.println(department.getDepartmentName());
        }
    }
    
    
    private int readMenuChoice() {

        while (!input.hasNextInt()) {

            System.out.println("\nError: Please enter a valid menu number.");
            input.nextLine();
            System.out.print("Choice: ");

        }

        int choice = input.nextInt();
        input.nextLine();

        return choice;

    }
}

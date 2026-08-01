package com.joysistvi.univenrollmentapp.model;

public class Department {

    private int id;
    private String department_name;

    public Department(int id, String department_name) {
        this.id = id;
        this.department_name = department_name;
    }
    public int getId(){
        return id;
    }
    public String getDepartmentName(){
        return department_name;
    }
}

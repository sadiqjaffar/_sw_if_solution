package com.googleapi.myapplication;

public class employeeRegister {

    String name,email,employeeId;


    public employeeRegister(String name,String email,String employeeId){

        this.name = name;
        this.email=email;
        this.employeeId=employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}

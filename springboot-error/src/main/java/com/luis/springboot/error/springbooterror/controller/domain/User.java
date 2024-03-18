package com.luis.springboot.error.springbooterror.controller.domain;


public class User {

    private int iD;
    private String name;
    private String lastName;
    private Role role;

    public User() {
    }

    public User(int iD, String name, String lastName, Role role) {
        this.iD = iD;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }

    public User(int iD, String name, String lastName) {
        this.iD = iD;
        this.name = name;
        this.lastName = lastName;
    }

    public int getiD() {
        return iD;
    }
    public void setiD(int iD) {
        this.iD = iD;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getRoleName() {
        return this.role.getName();
    }
    public void setRole(Role role) {
        this.role = role;
    }
    
}

package com.example;

public class windowStateInformation {

    private String employeeName;

    windowStateInformation() {
    }

    public synchronized void setEmployeeName(String name) {
        this.employeeName = name;
    }

    public synchronized String getEmployeeName() {
        return this.employeeName;
    }

}

package com.example.employeemgmt.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(int empid) {
        super("Employee with employee id " + empid + " not found");
    }
}

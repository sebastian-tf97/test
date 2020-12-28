package com.example.employeemgmt.exception;

public class NoEmployeesFoundException extends RuntimeException {
    public NoEmployeesFoundException() {
        super("No Employees found");
    }
}

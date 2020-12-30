package com.example.employeemgmt.exception;

/**
 * {@link NoEmployeesFoundException} class <br><br>
 * Thrown when no {@link com.example.employeemgmt.model.Employee} objects are found
 */
public class NoEmployeesFoundException extends RuntimeException {
    /**
     * {@link NoEmployeesFoundException} constructor
     */
    public NoEmployeesFoundException() {
        super("No Employees found");
    }
}

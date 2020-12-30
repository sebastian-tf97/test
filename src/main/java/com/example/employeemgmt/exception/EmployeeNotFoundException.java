package com.example.employeemgmt.exception;

/**
 * {@link EmployeeNotFoundException} class <br><br>
 * Thrown when a particular {@link com.example.employeemgmt.model.Employee} object is not found
 */
public class EmployeeNotFoundException extends RuntimeException {
    /**
     * {@link EmployeeNotFoundException} constructor
     * @param empid Employee id of the missing {@link com.example.employeemgmt.model.Employee} entity
     */
    public EmployeeNotFoundException(int empid) {
        super("Employee with employee id " + empid + " not found");
    }
}

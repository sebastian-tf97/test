package com.example.employeemgmt.exception;

/**
 * {@link EmployeeNotFoundException} class <br><br>
 * Thrown when a particular {@link com.example.employeemgmt.model.Employee} object is not found
 */
public class EmployeeNotFoundException extends RuntimeException {
    private final int empid;
    private final String errorCode;
    private final String errorMessage;

    /**
     * {@link EmployeeNotFoundException} constructor
     * @param empid Employee id of the missing {@link com.example.employeemgmt.model.Employee} entity
     */
    public EmployeeNotFoundException(ErrorCode errorCode, int empid) {
        this.empid = empid;
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public int getEmpid() {
        return empid;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

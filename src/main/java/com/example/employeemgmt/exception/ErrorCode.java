package com.example.employeemgmt.exception;

public enum ErrorCode {
    EMPLOYEE_NOT_FOUND("E001", "Employee Not Found"),
    NO_EMPLOYEES_FOUND("E002", "No Employees Found");

    private final String errorCode;
    private final String errorMessage;

    ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

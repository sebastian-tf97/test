package com.example.employeemgmt.exception;

/**
 * {@link NoEmployeesFoundException} class <br><br>
 * Thrown when no {@link com.example.employeemgmt.model.Employee} objects are found
 */
public class NoEmployeesFoundException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    /**
     * {@link NoEmployeesFoundException} constructor
     */
    public NoEmployeesFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

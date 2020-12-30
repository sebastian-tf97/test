package com.example.employeemgmt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link ControllerAdvice} exception handler class for {@link com.example.employeemgmt.controller.EmployeeController}
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * {@link ExceptionHandler} for {@link EmployeeNotFoundException}
     * @param ex {@link EmployeeNotFoundException} instance
     * @param request {@link WebRequest} instance
     * @return 404 Not Found {@link ResponseEntity}
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * {@link ExceptionHandler} for {@link NoEmployeesFoundException}
     * @param ex {@link NoEmployeesFoundException} instance
     * @param request {@link WebRequest} instance
     * @return 404 Not Found {@link ResponseEntity}
     */
    @ExceptionHandler(NoEmployeesFoundException.class)
    public ResponseEntity<Object> handleNoEmployeesFoundException(NoEmployeesFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * {@link ExceptionHandler} for {@link MethodArgumentNotValidException} <br><br>
     * Handle {@link com.example.employeemgmt.model.Employee} validation exceptions
     * @param ex {@link MethodArgumentNotValidException} instance
     * @param headers {@link HttpHeaders} headers
     * @param status {@link HttpStatus} status
     * @param request {@link WebRequest} instance
     * @return 400 Bad Request {@link ResponseEntity}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(field+" "+message);
        });
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

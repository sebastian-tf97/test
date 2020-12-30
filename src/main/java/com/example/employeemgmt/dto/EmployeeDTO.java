package com.example.employeemgmt.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * EmployeeDTO is the DTO for the {@link com.example.employeemgmt.model.Employee} entity. It abstracts age and gender of employees
 */
@Getter @Setter
public class EmployeeDTO {
    /**
     * Employee Id of the employee
     */
    private int empid;
    /**
     * Name of the employee
     */
    private String name;
    /**
     * Designation of the employee
     */
    private String designation;
    /**
     * Department that the employee belongs to
     */
    private String department;
}

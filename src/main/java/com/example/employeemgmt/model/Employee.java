package com.example.employeemgmt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Employee is the entity that represents an employee
 */
@Entity
@Table(name = "employees")
@Getter @Setter @ToString
public class Employee {
    /**
     * Employee Id of the employee
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empid;
    @NotEmpty
    /**
     * Name of the employee
    */
    private String name;
    /**
     * Age of the employee
    */
    private int age;
    /**
     * Gender of the employee
     * */
    private String gender;
    /**
     * Designation of the employee
    */
    @NotEmpty
    private String designation;
    /**
     * Department that the employee belongs to
    */
    private String department;
}

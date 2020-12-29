package com.example.employeemgmt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "employees")
@Getter @Setter @ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empid;
    @NotEmpty
    private String name;
    private int age;
    private String gender;
    @NotEmpty
    private String designation;
    private String department;
}

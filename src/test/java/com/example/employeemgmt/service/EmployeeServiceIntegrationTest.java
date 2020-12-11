package com.example.employeemgmt.service;

import com.example.employeemgmt.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceIntegrationTest {

    @Autowired
    EmployeeService employeeService;

    Employee employeeA;
    Employee employeeB;
    Employee employeeC;

    @BeforeAll
    void init() {
        employeeA = new Employee();
        employeeA.setName("Employee A");
        employeeA.setAge(23);
        employeeA.setGender("M");
        employeeA.setDesignation("Systems Engineer");
        employeeA.setDepartment("IT");

        employeeB = new Employee();
        employeeB.setName("Employee B");
        employeeB.setAge(23);
        employeeB.setGender("F");
        employeeB.setDesignation("Assistant Systems Engineer");
        employeeB.setDepartment("IT");

        employeeC = new Employee();
        employeeC.setName("Employee C");
        employeeC.setAge(23);
        employeeC.setGender("M");
        employeeC.setDesignation("Recruiter");
        employeeC.setDepartment("HR");

        employeeService.createEmployee(employeeA);
        employeeService.createEmployee(employeeB);
        employeeService.createEmployee(employeeC);
    }

    @Test
    public void getAllEmployees() {
        List<Employee> result = employeeService.getAllEmployees();

        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals("Employee A", result.get(0).getName());
        assertEquals("Employee B", result.get(1).getName());
        assertEquals("Employee C", result.get(2).getName());
    }

    @Test
    public void getEmployeeById() {
        List<Employee> employees = employeeService.getAllEmployees();
        Employee employeeAFromList = employees.get(0);

        Optional<Employee> result = employeeService.getEmployeeById(employeeAFromList.getEmpid());

        assertNotNull(result);
        assertEquals("Employee A", result.get().getName());
    }

    @Test
    public void getEmployeeByName() {
        List<Employee> result = employeeService.getEmployeeByName("Employee A");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Employee A", result.get(0).getName());
    }

    @Test
    public void createEmployee() {
        Employee newEmployee = new Employee();
        newEmployee.setName("New Employee");
        newEmployee.setAge(23);
        newEmployee.setGender("M");
        newEmployee.setDesignation("Recruiter");
        newEmployee.setDepartment("HR");

        Employee result = employeeService.createEmployee(newEmployee);

        assertEquals("New Employee", result.getName());

        List<Employee> employees = employeeService.getAllEmployees();

        assertFalse(employees.isEmpty());
        assertEquals(4, employees.size());
        assertEquals("New Employee", employees.get(3).getName());
    }
}
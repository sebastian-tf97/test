package com.example.employeemgmt.service;

import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    List<Employee> mockEmployees;
    Employee mockEmployeeA;
    Employee mockEmployeeB;
    Employee mockEmployeeC;

    @BeforeAll
    void init() {
        mockEmployees = new ArrayList<>();

        mockEmployeeA = new Employee();
        mockEmployeeA.setName("Employee A");
        mockEmployeeA.setAge(23);
        mockEmployeeA.setGender("M");
        mockEmployeeA.setDesignation("Systems Engineer");
        mockEmployeeA.setDepartment("IT");

        mockEmployeeB = new Employee();
        mockEmployeeB.setName("Employee B");
        mockEmployeeB.setAge(23);
        mockEmployeeB.setGender("F");
        mockEmployeeB.setDesignation("Assistant Systems Engineer");
        mockEmployeeB.setDepartment("IT");

        mockEmployeeC = new Employee();
        mockEmployeeC.setName("Employee C");
        mockEmployeeC.setAge(23);
        mockEmployeeC.setGender("M");
        mockEmployeeC.setDesignation("Recruiter");
        mockEmployeeC.setDepartment("HR");

        mockEmployees.add(mockEmployeeA);
        mockEmployees.add(mockEmployeeB);
        mockEmployees.add(mockEmployeeC);
    }

    @Test
    void getAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        List<Employee> result = employeeService.getAllEmployees();

        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals("Employee A", result.get(0).getName());
        assertEquals("Employee B", result.get(1).getName());
        assertEquals("Employee C", result.get(2).getName());
    }

    @Test
    void getEmployeeById() {
        when(employeeRepository.findById(0)).thenReturn(Optional.of(mockEmployeeA));

        Employee result = employeeService.getEmployeeById(0);

        assertNotNull(result);
        assertEquals("Employee A", result.getName());
    }

    @Test
    void getEmployeeByName() {
        when(employeeRepository.findByName("Employee A")).thenReturn(new ArrayList<>(Arrays.asList(mockEmployeeA)));

        List<Employee> result = employeeService.getEmployeeByName("Employee A");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Employee A", result.get(0).getName());
    }

    @Test
    void createEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployeeA);

        Employee result = employeeService.createEmployee(new Employee());

        assertEquals("Employee A", result.getName());
    }
}
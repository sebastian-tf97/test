package com.example.employeemgmt.controller;

import com.example.employeemgmt.dto.EmployeeDTO;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

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
    void getAllEmployees() {
        String uri = "http://localhost:"+port+"/employees";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<EmployeeDTO[]> responseEntity = testRestTemplate.exchange(uri, HttpMethod.GET, httpEntity, EmployeeDTO[].class);

        assertNotNull(responseEntity.getBody());
        assertEquals(3, responseEntity.getBody().length);
        assertEquals("Employee A", responseEntity.getBody()[0].getName());
    }

    @Test
    void getEmployeeByEmpid() {
        String uri = "http://localhost:"+port+"/employees/1";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<EmployeeDTO> responseEntity = testRestTemplate.exchange(uri, HttpMethod.GET, httpEntity, EmployeeDTO.class);

        assertNotNull(responseEntity.getBody());
        assertEquals("Employee A", responseEntity.getBody().getName());
    }

    @Test
    void getEmployeeByName() {
        String uri = "http://localhost:"+port+"/employees?name=Employee A";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<EmployeeDTO[]> responseEntity = testRestTemplate.exchange(uri, HttpMethod.GET, httpEntity, EmployeeDTO[].class);

        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().length);
        assertEquals("Employee A", responseEntity.getBody()[0].getName());
    }

    @Test
    void createEmployee() {
        String uri = "http://localhost:"+port+"/employees";

        Employee newEmployee = new Employee();
        newEmployee.setName("New Employee");
        newEmployee.setAge(23);
        newEmployee.setGender("M");
        newEmployee.setDesignation("Recruiter");
        newEmployee.setDepartment("HR");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Employee> httpEntity = new HttpEntity<>(newEmployee, headers);
        ResponseEntity<EmployeeDTO> responseEntity = testRestTemplate.exchange(uri, HttpMethod.POST, httpEntity, EmployeeDTO.class);

        assertNotNull(responseEntity.getBody());
        assertEquals("New Employee", responseEntity.getBody().getName());
    }
}
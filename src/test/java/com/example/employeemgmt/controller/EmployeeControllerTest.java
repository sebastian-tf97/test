package com.example.employeemgmt.controller;

import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    List<Employee> mockEmployees;
    Employee mockEmployeeA;
    Employee mockEmployeeB;
    Employee mockEmployeeC;

    String mockEmployeesJSON;
    String mockEmployeeAJSON;
    String mockEmployeesByNameJSON;

    @BeforeAll()
    void init() throws JsonProcessingException {
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

        mockEmployeesJSON = this.mapToJson(mockEmployees);
        mockEmployeeAJSON = this.mapToJson(mockEmployeeA);
        mockEmployeesByNameJSON = this.mapToJson(new ArrayList<>(Arrays.asList(mockEmployeeA)));
    }

    @Test
    void getAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);

        MvcResult result = mockMvc
                .perform(get("/employees"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseJSON = response.getContentAsString();

        assertEquals(mockEmployeesJSON, responseJSON);
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void getEmployeeByEmpid() throws Exception {
        when(employeeService.getEmployeeById(1)).thenReturn(Optional.of(mockEmployeeA));

        MvcResult result = mockMvc
                .perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseJSON = response.getContentAsString();

        assertEquals(mockEmployeeAJSON, responseJSON);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getEmployeeByName() throws Exception {
        when(employeeService.getEmployeeByName(any(String.class))).thenReturn(new ArrayList<>(Arrays.asList(mockEmployeeA)));

        MvcResult result = mockMvc
                .perform(get("/employees?name=Employee A"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseJSON = response.getContentAsString();

        assertEquals(mockEmployeesByNameJSON, responseJSON);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void createEmployee() throws Exception {
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(mockEmployeeA);

        String newEmployeeJSON = this.mapToJson(new Employee());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeJSON);

        MvcResult result = mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseJSON = response.getContentAsString();

        assertEquals(mockEmployeeAJSON, responseJSON);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
package com.example.employeemgmt.controller;

import com.example.employeemgmt.dto.EmployeeDTO;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(EmployeeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    List<EmployeeDTO> mockEmployeesDTO;
    EmployeeDTO mockEmployeeADTO;
    EmployeeDTO mockEmployeeBDTO;
    EmployeeDTO mockEmployeeCDTO;

    String mockEmployeesDTOJSON;
    String mockEmployeeADTOJSON;
    String mockEmployeesByNameDTOJSON;

    @BeforeAll()
    void init() throws JsonProcessingException {
        mockEmployeesDTO = new ArrayList<>();

        mockEmployeeADTO = new EmployeeDTO();
        mockEmployeeADTO.setName("Employee A");
        mockEmployeeADTO.setDesignation("Systems Engineer");
        mockEmployeeADTO.setDepartment("IT");

        mockEmployeeBDTO = new EmployeeDTO();
        mockEmployeeBDTO.setName("Employee B");
        mockEmployeeBDTO.setDesignation("Assistant Systems Engineer");
        mockEmployeeBDTO.setDepartment("IT");

        mockEmployeeCDTO = new EmployeeDTO();
        mockEmployeeCDTO.setName("Employee C");
        mockEmployeeCDTO.setDesignation("Recruiter");
        mockEmployeeCDTO.setDepartment("HR");

        mockEmployeesDTO.add(mockEmployeeADTO);
        mockEmployeesDTO.add(mockEmployeeBDTO);
        mockEmployeesDTO.add(mockEmployeeCDTO);

        mockEmployeesDTOJSON = this.mapToJson(mockEmployeesDTO);
        mockEmployeeADTOJSON = this.mapToJson(mockEmployeeADTO);
        mockEmployeesByNameDTOJSON = this.mapToJson(new ArrayList<>(Arrays.asList(mockEmployeeADTO)));
    }

    @Test
    void getAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(mockEmployeesDTO);

        MvcResult result = mockMvc
                .perform(get("/employees"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseJSON = response.getContentAsString();

        assertEquals(mockEmployeesDTOJSON, responseJSON);
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void getEmployeeByEmpid() throws Exception {
        when(employeeService.getEmployeeById(1)).thenReturn(mockEmployeeADTO);

        MvcResult result = mockMvc
                .perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseJSON = response.getContentAsString();

        assertEquals(mockEmployeeADTOJSON, responseJSON);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getEmployeeByName() throws Exception {
        when(employeeService.getEmployeeByName(any(String.class))).thenReturn(new ArrayList<>(Arrays.asList(mockEmployeeADTO)));

        MvcResult result = mockMvc
                .perform(get("/employees?name=Employee A"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseJSON = response.getContentAsString();

        assertEquals(mockEmployeesByNameDTOJSON, responseJSON);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void createEmployee() throws Exception {
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(mockEmployeeADTO);

        Employee newEmployee = new Employee();
        newEmployee.setName("Employee A");
        newEmployee.setAge(23);
        newEmployee.setGender("M");
        newEmployee.setDesignation("Systems Engineer");
        newEmployee.setDepartment("IT");

        String newEmployeeJSON = this.mapToJson(newEmployee);

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

        assertEquals(mockEmployeeADTOJSON, responseJSON);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
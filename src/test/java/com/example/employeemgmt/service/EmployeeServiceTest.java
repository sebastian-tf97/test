package com.example.employeemgmt.service;

import com.example.employeemgmt.dto.EmployeeDTO;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    EmployeeService employeeService;

    List<Employee> mockEmployees;
    Employee mockEmployeeA;
    EmployeeDTO mockEmployeeADTO;
    Employee mockEmployeeB;
    EmployeeDTO mockEmployeeBDTO;
    Employee mockEmployeeC;
    EmployeeDTO mockEmployeeCDTO;

    @BeforeAll
    void init() {
        mockEmployees = new ArrayList<>();

        mockEmployeeA = new Employee();
        mockEmployeeA.setName("Employee A");
        mockEmployeeA.setAge(23);
        mockEmployeeA.setGender("M");
        mockEmployeeA.setDesignation("Systems Engineer");
        mockEmployeeA.setDepartment("IT");

        mockEmployeeADTO = new EmployeeDTO();
        mockEmployeeADTO.setName("Employee A");
        mockEmployeeADTO.setDesignation("Systems Engineer");
        mockEmployeeADTO.setDepartment("IT");


        mockEmployeeB = new Employee();
        mockEmployeeB.setName("Employee B");
        mockEmployeeB.setAge(23);
        mockEmployeeB.setGender("F");
        mockEmployeeB.setDesignation("Assistant Systems Engineer");
        mockEmployeeB.setDepartment("IT");

        mockEmployeeBDTO = new EmployeeDTO();
        mockEmployeeBDTO.setName("Employee B");
        mockEmployeeBDTO.setDesignation("Assistant Systems Engineer");
        mockEmployeeBDTO.setDepartment("IT");


        mockEmployeeC = new Employee();
        mockEmployeeC.setName("Employee C");
        mockEmployeeC.setAge(23);
        mockEmployeeC.setGender("M");
        mockEmployeeC.setDesignation("Recruiter");
        mockEmployeeC.setDepartment("HR");

        mockEmployeeCDTO = new EmployeeDTO();
        mockEmployeeCDTO.setName("Employee C");
        mockEmployeeCDTO.setDesignation("Recruiter");
        mockEmployeeCDTO.setDepartment("HR");

        mockEmployees.add(mockEmployeeA);
        mockEmployees.add(mockEmployeeB);
        mockEmployees.add(mockEmployeeC);
    }

    @Test
    void getAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(mockEmployees);
        when(modelMapper.map(mockEmployeeA, EmployeeDTO.class)).thenReturn(mockEmployeeADTO);
        when(modelMapper.map(mockEmployeeB, EmployeeDTO.class)).thenReturn(mockEmployeeBDTO);
        when(modelMapper.map(mockEmployeeC, EmployeeDTO.class)).thenReturn(mockEmployeeCDTO);

        List<EmployeeDTO> result = employeeService.getAllEmployees();

        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals("Employee A", result.get(0).getName());
        assertEquals("Employee B", result.get(1).getName());
        assertEquals("Employee C", result.get(2).getName());
    }

    @Test
    void getEmployeeById() {
        when(employeeRepository.findById(0)).thenReturn(Optional.of(mockEmployeeA));
        when(modelMapper.map(mockEmployeeA, EmployeeDTO.class)).thenReturn(mockEmployeeADTO);

        EmployeeDTO result = employeeService.getEmployeeById(0);

        assertNotNull(result);
        assertEquals("Employee A", result.getName());
    }

    @Test
    void getEmployeeByName() {
        when(employeeRepository.findByName("Employee A")).thenReturn(new ArrayList<>(Arrays.asList(mockEmployeeA)));
        when(modelMapper.map(mockEmployeeA, EmployeeDTO.class)).thenReturn(mockEmployeeADTO);

        List<EmployeeDTO> result = employeeService.getEmployeeByName("Employee A");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Employee A", result.get(0).getName());
    }

    @Test
    void createEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployeeA);
        when(modelMapper.map(mockEmployeeA, EmployeeDTO.class)).thenReturn(mockEmployeeADTO);

        EmployeeDTO result = employeeService.createEmployee(new Employee());

        assertEquals("Employee A", result.getName());
    }
}
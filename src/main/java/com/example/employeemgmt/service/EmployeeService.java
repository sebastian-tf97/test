package com.example.employeemgmt.service;

import com.example.employeemgmt.dto.EmployeeDTO;
import com.example.employeemgmt.exception.EmployeeNotFoundException;
import com.example.employeemgmt.exception.ErrorCode;
import com.example.employeemgmt.exception.NoEmployeesFoundException;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class that utilizes {@link EmployeeRepository} to access and create {@link Employee} entities
 */
@Service
public class EmployeeService {

    /**
     * Autowired {@link ModelMapper} instance
     */
    @Autowired
    private EmployeeRepository employeeRepo;

    /**
     * Autowired {@link EmployeeRepository} instance
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method that fetches all Employees
     * @return a {@link List} of {@link EmployeeDTO} objects converted from {@link Employee} objects
     */
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees = ((List<Employee>) employeeRepo.findAll())
                                                        .stream()
                                                        .map(this::convertToDTO)
                                                        .collect(Collectors.toList());

        if(employees.isEmpty()) throw new NoEmployeesFoundException(ErrorCode.NO_EMPLOYEES_FOUND);

        return employees;
    }

    /**
     * Method that finds a single employee by Employee Id - {@link Employee#empid}
     * @param empid the Employee id to be searched for
     * @return a single {@link EmployeeDTO} object converted from {@link Employee} object
     */
    public EmployeeDTO getEmployeeById(int empid) {
        Employee employee =  employeeRepo.findById(empid).orElseThrow(() -> new EmployeeNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND, empid));
        return convertToDTO(employee);
    }

    /**
     * Method that finds employees that matches a given name - {@link Employee#name}
     * @param name the Employee name to be searched for
     * @return a {@link List} of {@link EmployeeDTO} objects converted from {@link Employee} objects
     */
    public List<EmployeeDTO> getEmployeeByName(String name) {
        return employeeRepo.findByName(name)
                                    .stream()
                                    .map(this::convertToDTO)
                                    .collect(Collectors.toList());
    }

    /**
     * Method that creates a new Employee - {@link Employee}
     * @param employee the {@link Employee} object to be created
     * @return an {@link EmployeeDTO} object converted from the newly created {@link Employee} object
     */
    public EmployeeDTO createEmployee(Employee employee) {
        return convertToDTO(employeeRepo.save(employee));
    }

    /**
     * Method to find employees that are older than a given minimum age, matching a given gender and belongs to a given department
     * @param minAge the minimum age of the employees to be searched for
     * @param gender the age of the employees to be searched for
     * @param department the department of the employees to be searched for
     * @return a {@link List} of {@link EmployeeDTO} objects converted from {@link Employee} objects that matches the given minimum age, gender and department
     */
    public List<EmployeeDTO> getEmployeesByAgeGenderDept(int minAge, String gender, String department) {
        return employeeRepo.findByAgeGenderDept(minAge, gender, department)
                                                                .stream()
                                                                .map(this::convertToDTO)
                                                                .collect(Collectors.toList());
    }

    /**
     * Helper method used to convert {@link Employee} objects to {@link EmployeeDTO} objects
     * @param employee the {@link Employee} object to be converted
     * @return a {@link EmployeeDTO} object converted from the given {@link Employee} object
     */
    private EmployeeDTO convertToDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }
}

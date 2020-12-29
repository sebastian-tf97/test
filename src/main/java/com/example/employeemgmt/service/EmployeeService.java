package com.example.employeemgmt.service;

import com.example.employeemgmt.dto.EmployeeDTO;
import com.example.employeemgmt.exception.EmployeeNotFoundException;
import com.example.employeemgmt.exception.NoEmployeesFoundException;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees = ((List<Employee>) employeeRepo.findAll())
                                                        .stream()
                                                        .map(this::convertToDTO)
                                                        .collect(Collectors.toList());

        if(employees.isEmpty()) throw new NoEmployeesFoundException();

        return employees;
    }

    public EmployeeDTO getEmployeeById(int empid) {
        Employee employee =  employeeRepo.findById(empid).orElseThrow(() -> new EmployeeNotFoundException(empid));
        return convertToDTO(employee);
    }

    public List<EmployeeDTO> getEmployeeByName(String name) {
        return employeeRepo.findByName(name)
                                    .stream()
                                    .map(this::convertToDTO)
                                    .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(Employee employee) {
        return convertToDTO(employeeRepo.save(employee));
    }

    public List<EmployeeDTO> getEmployeesByAgeGenderDept(int minAge, String gender, String department) {
        return employeeRepo.findByAgeGenderDept(minAge, gender, department)
                                                                .stream()
                                                                .map(this::convertToDTO)
                                                                .collect(Collectors.toList());
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }
}

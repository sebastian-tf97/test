package com.example.employeemgmt.controller;

import com.example.employeemgmt.dto.EmployeeDTO;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class that handles the Employee Management REST API
 */
@RestController
public class EmployeeController {

    /**
     * Autowired {@link EmployeeService} instance
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Handler for {@code GET /employees} endpoint <br><br>
     * Get all employees
     * @return a {@link List} of {@link EmployeeDTO} objects
     */
    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * Handler for {@code GET /employees/{empid}} endpoint <br><br>
     * Get employee by Employee id
     * @param empid the Employee id to be searched for
     * @return a single {@link EmployeeDTO} object
     */
    @GetMapping("/employees/{empid}")
    public EmployeeDTO getEmployeeByEmpid(@PathVariable int empid) {
        return employeeService.getEmployeeById(empid);
    }

    /**
     * Handler for {@code GET /employees?name={name}} endpoint <br><br>
     * Get employee by Employee name
     * @param name the Employee name to be searched for
     * @return a {@link List} of {@link EmployeeDTO} objects
     */
    @GetMapping(value = "/employees", params = "name")
    public List<EmployeeDTO> getEmployeeByName(@RequestParam(value = "name") String name) {
        return employeeService.getEmployeeByName(name);
    }

    /**
     * Handler for {@code POST /employees} endpoint <br><br>
     * Create/POST a new Employee
     * @param employee the {@link Employee} object to be created
     * @return an {@link EmployeeDTO} object
     */
    @PostMapping("/employees")
    public EmployeeDTO createEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.createEmployee(employee);
    }

    /**
     * Handler for {@code GET /employees?minAge={minAge}&gender={gender}&department={department}} endpoint <br><br>
     * Get employee by Employee minimum age, gender and department
     * @param minAge the minimum age of the employees to be searched for
     * @param gender the age of the employees to be searched for
     * @param department the department of the employees to be searched for
     * @return a {@link List} of {@link EmployeeDTO} objects
     */
    @GetMapping(value = "/employees", params = {"minAge", "gender", "department"})
    public List<EmployeeDTO> getEmployeeByName(@RequestParam(value = "minAge") int minAge, @RequestParam(value = "gender") String gender, @RequestParam(value = "department") String department) {
        return employeeService.getEmployeesByAgeGenderDept(minAge, gender, department);
    }

}

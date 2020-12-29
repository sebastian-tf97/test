package com.example.employeemgmt.controller;

import com.example.employeemgmt.dto.EmployeeDTO;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{empid}")
    public EmployeeDTO getEmployeeByEmpid(@PathVariable int empid) {
        return employeeService.getEmployeeById(empid);
    }

    @GetMapping(value = "/employees", params = "name")
    public List<EmployeeDTO> getEmployeeByName(@RequestParam(value = "name") String name) {
        return employeeService.getEmployeeByName(name);
    }

    @PostMapping("/employees")
    public EmployeeDTO createEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping(value = "/employees", params = {"minAge", "gender", "department"})
    public List<EmployeeDTO> getEmployeeByName(@RequestParam(value = "minAge") int minAge, @RequestParam(value = "gender") String gender, @RequestParam(value = "department") String department) {
        return employeeService.getEmployeesByAgeGenderDept(minAge, gender, department);
    }

}

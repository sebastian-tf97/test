package com.example.employeemgmt.controller;

import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{empid}")
    public Optional<Employee> getEmployeeByEmpid(@PathVariable int empid) {
        return employeeService.getEmployeeById(empid);
    }

    @GetMapping(value = "/employees", params = "name")
    public List<Employee> getEmployeeByName(@RequestParam(value = "name") String name) {
        return employeeService.getEmployeeByName(name);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

}

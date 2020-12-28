package com.example.employeemgmt.service;

import com.example.employeemgmt.exception.EmployeeNotFoundException;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepo.findAll();
    }

    public Employee getEmployeeById(int empid) {
        return employeeRepo.findById(empid).orElseThrow(() -> new EmployeeNotFoundException(empid));
    }

    public List<Employee> getEmployeeByName(String name) {
        return employeeRepo.findByName(name);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
}

package com.example.employeemgmt.service;

import com.example.employeemgmt.exception.EmployeeNotFoundException;
import com.example.employeemgmt.exception.NoEmployeesFoundException;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepo.findAll();

        if(employees.isEmpty()) throw new NoEmployeesFoundException();

        return employees;
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

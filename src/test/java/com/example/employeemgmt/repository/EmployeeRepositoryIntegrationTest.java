package com.example.employeemgmt.repository;

import com.example.employeemgmt.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryIntegrationTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void findByName() {
        Employee employeeA = new Employee();
        employeeA.setName("Employee A");
        employeeA.setAge(23);
        employeeA.setGender("M");
        employeeA.setDesignation("Systems Engineer");
        employeeA.setDepartment("IT");

        testEntityManager.persist(employeeA);

        List<Employee> result = employeeRepository.findByName("Employee A");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Employee A", result.get(0).getName());
    }
}
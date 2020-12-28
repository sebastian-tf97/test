package com.example.employeemgmt.repository;

import com.example.employeemgmt.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByName(String name);

    @Query("FROM Employee WHERE age >= ?1 AND gender = ?2 AND department = ?3 ORDER BY age ASC")
    List<Employee> findByAgeGenderDept(int minAge, String gender, String department);
}

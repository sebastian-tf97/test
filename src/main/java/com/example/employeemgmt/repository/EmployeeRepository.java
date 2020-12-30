package com.example.employeemgmt.repository;

import com.example.employeemgmt.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface to perform CRUD operations for the {@link Employee} entity
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    /**
     * Method to fetch employees that matches a given name
     * @param name the name to be searched for
     * @return a {@link List} of {@link Employee} objects that matches the given name
     */
    List<Employee> findByName(String name);

    /**
     * Method to fetch employees that are older than a given minimum age, matching a given gender and belongs to a given department
     * @param minAge the minimum age of the employees to be searched for
     * @param gender the age of the employees to be searched for
     * @param department the department of the employees to be searched for
     * @return a {@link List} of {@link Employee} objects that matches the given minimum age, gender and department
     */
    @Query("FROM Employee WHERE age >= ?1 AND gender = ?2 AND department = ?3 ORDER BY age ASC")
    List<Employee> findByAgeGenderDept(int minAge, String gender, String department);
}

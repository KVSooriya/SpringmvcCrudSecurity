package com.vismee.springmvccrudsecurity.repository;

import com.vismee.springmvccrudsecurity.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
    /* Custom JPA Query - Spring Data JPA will parse the method name and based on the format and pattern
       it will create the appropriate query
    */
    public List<Employee> findAllByOrderByFirstNameAsc();
}

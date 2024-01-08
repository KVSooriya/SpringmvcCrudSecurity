package com.vismee.springmvccrudsecurity.service;

import com.vismee.springmvccrudsecurity.entity.Employee;

import java.util.List;

public interface EmployeeService
{
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
}

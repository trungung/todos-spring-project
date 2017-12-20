package com.example.todosproject.service;

import com.example.todosproject.model.Employee;

import java.util.List;

public interface EmployeeService {

    void create(Employee employee);

    Employee getEmployeeById(long id);

    List<Employee> getAllEmployees();
}
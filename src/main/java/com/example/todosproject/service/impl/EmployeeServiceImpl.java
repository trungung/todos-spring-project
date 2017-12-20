package com.example.todosproject.service.impl;

import com.example.todosproject.model.Employee;
import com.example.todosproject.repository.EmployeeRepository;
import com.example.todosproject.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void create(Employee employee) {
        Employee existing = employeeRepository.findOne(employee.getId());
        Assert.isNull(existing, "Employee already exists: " + employee.getId());

        employeeRepository.save(employee);

        log.info("new employee has been created: {}", employee.getName());
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
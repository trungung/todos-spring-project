package com.example.todosproject.controller;

import com.example.todosproject.model.Employee;
import com.example.todosproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") long id) {
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@Valid @RequestBody Employee employee) {
        employeeService.create(employee);
    }
}

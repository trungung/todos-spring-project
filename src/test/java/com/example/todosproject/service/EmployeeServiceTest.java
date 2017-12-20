package com.example.todosproject.service;

import com.example.todosproject.model.Employee;
import com.example.todosproject.repository.EmployeeRepository;
import com.example.todosproject.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        employee = new Employee();
        employee.setId(1L);
        employee.setName("name");
        employee.setLastName("last name");
        employee.setActive(true);
    }

    @Test
    public void shouldCreateEmployee() {

        employeeRepository.save(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenUserAlreadyExists() {

        when(employeeRepository.findOne(employee.getId())).thenReturn(new Employee());
        employeeService.create(employee);
    }

    @Test
    public void shouldGetEmployeeByIdSuccess() {

        when(employeeRepository.findOne(employee.getId())).thenReturn(employee);

        assertNotNull(employeeService);
        assertNotNull(employee);

        Employee found = employeeService.getEmployeeById(employee.getId());

        assertEquals(found, employee);
    }
}

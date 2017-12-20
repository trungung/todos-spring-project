package com.example.todosproject.repository;

import com.example.todosproject.DemoApplication;
import com.example.todosproject.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void shouldSaveAndFindEmployeeById() {

        Employee employee = new Employee();
        employee.setName("name");
        employee.setLastName("last name");
        employee.setActive(true);

        employeeRepository.save(employee);

        Employee found = employeeRepository.findOne(employee.getId());

        assertNotNull(found);
        assertEquals(employee.getName(), found.getName());
        assertEquals(employee.getLastName(), found.getLastName());
        assertEquals(employee.isActive(), found.isActive());
    }
}

package com.example.todosproject.controller;

import com.example.todosproject.DemoApplication;
import com.example.todosproject.model.Employee;
import com.example.todosproject.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class EmployeeControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private MockMvc mockMvc;

    private Employee employee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        employee = new Employee();
        employee.setId(1L);
        employee.setName("name");
        employee.setLastName("last name");
        employee.setActive(true);

    }

    @Test
    public void shouldCreateNewEmployee() throws Exception {

        String json = mapper.writeValueAsString(employee);

        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailWhenEmployeeIsNotValid() throws Exception {

        final Employee employee = new Employee();
        employee.setId(2L);
        employee.setName("name1");
        employee.setLastName("last name 1");
        employee.setActive(true);

        mockMvc.perform(post("/employee"))
                .andExpect(status().isBadRequest());
    }
}

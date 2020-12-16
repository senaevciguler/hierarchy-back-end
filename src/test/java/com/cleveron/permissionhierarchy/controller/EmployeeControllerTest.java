package com.cleveron.permissionhierarchy.controller;

import com.cleveron.permissionhierarchy.modal.Employee;
import com.cleveron.permissionhierarchy.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        //given
        given(employeeService.findAll()).willReturn(List.of(new Employee()));

        //when
        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk());

        //then
        then(employeeService).should().findAll();
    }

    @Test
    void findById() throws Exception {
        //given
        given(employeeService.findById(1L)).willReturn(Optional.of(new Employee()));

        //when
        mockMvc.perform(get("/api/v1/employee/{id}", "1"))
                .andExpect(status().isOk());

        //then
        then(employeeService).should().findById(1L);
    }

    @Test
    void save() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Employee.builder().id(1L).build());
        given(employeeService.save(any())).willReturn(new Employee());

        //when
        mockMvc.perform(post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        //then
        then(employeeService).should().save(any());
    }

    @Test
    void update() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Employee.builder().name("test").build());
        given(employeeService.update(1L, Employee.builder().name("test").build())).willReturn(new Employee());

        //when
        mockMvc.perform(put("/api/v1/employee/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        //then
        then(employeeService).should().update(1L, Employee.builder().name("test").build());
    }

    @Test
    void deleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/v1/employee/{id}", 1L)).andExpect(status().isOk());
    }
}
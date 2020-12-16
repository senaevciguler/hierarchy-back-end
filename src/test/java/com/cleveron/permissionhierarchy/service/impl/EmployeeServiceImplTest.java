package com.cleveron.permissionhierarchy.service.impl;

import com.cleveron.permissionhierarchy.modal.Employee;
import com.cleveron.permissionhierarchy.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atMost;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void getAll() {
        //given
        Employee employee = new Employee();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        given(employeeRepository.findAll()).willReturn(employees);

        //when
        List<Employee> foundEmployees = service.findAll();

        //then
        then(employeeRepository).should().findAll();
        assertThat(foundEmployees).hasSize(1);
    }

    @Test
    void findById() {
        //given
        Employee employee = new Employee();
        given(employeeRepository.findById(anyLong())).willReturn(Optional.of(employee));

        //when
        Optional<Employee> foundEmployee = service.findById(1L);

        //then
        then(employeeRepository).should().findById(anyLong());
        assertThat(foundEmployee).isNotNull();
    }

    @Test
    void create() {
        //given
        Employee employee = new Employee();
        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        //when
        Employee savedEmployee = service.save(new Employee());

        //then
        then(employeeRepository).should().save(any(Employee.class));
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void update() {
        //given
        Employee employee = new Employee();
        given(employeeRepository.findById(anyLong())).willReturn(Optional.of(employee));
        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        //when
        Employee savedEmployee = service.update(1L, new Employee());

        //then
        then(employeeRepository).should().save(any(Employee.class));
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    void delete() {
        //when
        service.delete(anyLong());

        //then
        then(employeeRepository).should(atMost(2)).deleteById(anyLong());
    }
}
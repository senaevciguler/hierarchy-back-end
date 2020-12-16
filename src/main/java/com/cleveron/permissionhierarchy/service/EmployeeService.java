package com.cleveron.permissionhierarchy.service;

import com.cleveron.permissionhierarchy.modal.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);

    Employee update(Long id, Employee employee);

    void delete(Long id);
}

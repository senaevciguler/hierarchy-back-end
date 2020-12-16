package com.cleveron.permissionhierarchy.service.impl;

import com.cleveron.permissionhierarchy.modal.Employee;
import com.cleveron.permissionhierarchy.repository.EmployeeRepository;
import com.cleveron.permissionhierarchy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee update(Long id, Employee employee) {
        if (employeeRepository.findById(id).isPresent()) {
            employee.setId(id);
            return employeeRepository.save(employee);
        }

        return new Employee();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}

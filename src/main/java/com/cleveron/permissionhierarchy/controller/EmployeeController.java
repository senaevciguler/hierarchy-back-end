package com.cleveron.permissionhierarchy.controller;

import com.cleveron.permissionhierarchy.modal.Employee;
import com.cleveron.permissionhierarchy.service.EmployeeService;
import com.cleveron.permissionhierarchy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public Result findAll() {
        log.info("Employee findAll call");
        return Result.Success.builder()
                .payload(employeeService.findAll())
                .build();
    }

    @GetMapping("/employee/{id}")
    public Result findById(@PathVariable Long id) {
        log.info("Employee findById call by id:", id);
        return Result.Success.builder()
                .payload(employeeService.findById(id))
                .build();
    }

    @PostMapping("/employee")
    public Result save(@RequestBody Employee employee) {
        return Result.Success.builder()
                .message("employee saved successfully")
                .payload(employeeService.save(employee))
                .build();
    }

    @PutMapping("/employee/{id}")
    public Result update(@PathVariable long id, @Valid @RequestBody Employee employee) {
        return Result.Success.builder()
                .message("employee updated successfully")
                .payload(employeeService.update(id, employee))
                .build();
    }

    @DeleteMapping("/employee/{id}")
    public Result delete(@PathVariable Long id) {
        employeeService.delete(id);
        return Result.Success.builder()
                .message("employee deleted successfully")
                .build();
    }
}

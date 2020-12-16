package com.cleveron.permissionhierarchy.controller;

import com.cleveron.permissionhierarchy.modal.Role;
import com.cleveron.permissionhierarchy.service.RoleService;
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
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public Result findAll() {
        log.info("Role findAll call");
        return Result.Success.builder()
                .payload(roleService.findAll())
                .build();
    }

    @GetMapping("/roles/{id}")
    public Result findById(@PathVariable Long id) {
        log.info("Role findById call by id:", id);
        return Result.Success.builder()
                .payload(roleService.findById(id))
                .build();
    }

    @PostMapping("/roles")
    public Result save(@RequestBody Role role) {
        return Result.Success.builder()
                .message("Role saved successfully")
                .payload(roleService.save(role))
                .build();
    }

    @PutMapping("/roles/{id}")
    public Result update(@PathVariable long id, @Valid @RequestBody Role role) {
        return Result.Success.builder()
                .message("Role updated successfully")
                .payload(roleService.update(id, role))
                .build();
    }

    @DeleteMapping("/roles/{id}")
    public Result delete(@PathVariable Long id) {
        roleService.delete(id);
        return Result.Success.builder()
                .message("Role deleted successfully")
                .build();
    }
}

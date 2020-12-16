package com.cleveron.permissionhierarchy.controller;

import com.cleveron.permissionhierarchy.modal.Permission;
import com.cleveron.permissionhierarchy.service.PermissionService;
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
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permissions")
    public Result findAll() {
        log.info("Permission findAll call");
        return Result.Success.builder()
                .payload(permissionService.findAll())
                .build();
    }

    @GetMapping("/permissions/{id}")
    public Result findById(@PathVariable Long id) {
        log.info("Permission findById call by id:", id);
        return Result.Success.builder()
                .payload(permissionService.findById(id))
                .build();
    }

    @PostMapping("/permissions")
    public Result save(@RequestBody Permission permission) {
        return Result.Success.builder()
                .message("Permission saved successfully")
                .payload(permissionService.save(permission))
                .build();
    }

    @PutMapping("/permissions/{id}")
    public Result update(@PathVariable long id, @Valid @RequestBody Permission permission) {
        return Result.Success.builder()
                .message("Permission updated successfully")
                .payload(permissionService.update(id, permission))
                .build();
    }

    @DeleteMapping("/permissions/{id}")
    public Result delete(@PathVariable Long id) {
        permissionService.delete(id);
        return Result.Success.builder()
                .message("Permission deleted successfully")
                .build();
    }
}

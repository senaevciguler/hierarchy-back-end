package com.cleveron.permissionhierarchy.controller;

import com.cleveron.permissionhierarchy.modal.Privilege;
import com.cleveron.permissionhierarchy.service.PrivilegeService;
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
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @GetMapping("/privileges")
    public Result findAll() {
        log.info("Privilege findAll call");
        return Result.Success.builder()
                .payload(privilegeService.findAll())
                .build();
    }

    @GetMapping("/privileges/{id}")
    public Result findById(@PathVariable Long id) {
        log.info("Privilege findById call by id:", id);
        return Result.Success.builder()
                .payload(privilegeService.findById(id))
                .build();
    }

    @PostMapping("/privileges")
    public Result save(@RequestBody Privilege privilege) {
        return Result.Success.builder()
                .message("Privilege saved successfully")
                .payload(privilegeService.save(privilege))
                .build();
    }

    @PutMapping("/privileges/{id}")
    public Result update(@PathVariable long id, @Valid @RequestBody Privilege privilege) {
        return Result.Success.builder()
                .message("Privilege updated successfully")
                .payload(privilegeService.update(id, privilege))
                .build();
    }

    @DeleteMapping("/privileges/{id}")
    public Result delete(@PathVariable Long id) {
        privilegeService.delete(id);
        return Result.Success.builder()
                .message("Privilege deleted successfully")
                .build();
    }
}

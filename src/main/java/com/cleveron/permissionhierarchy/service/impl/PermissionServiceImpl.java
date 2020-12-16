package com.cleveron.permissionhierarchy.service.impl;

import com.cleveron.permissionhierarchy.modal.Permission;
import com.cleveron.permissionhierarchy.repository.PermissionRepository;
import com.cleveron.permissionhierarchy.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Long id, Permission permission) {
        if (permissionRepository.findById(id).isPresent()) {
            permission.setId(id);
            return permissionRepository.save(permission);
        }
        return new Permission();
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);

    }
}

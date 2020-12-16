package com.cleveron.permissionhierarchy.service.impl;

import com.cleveron.permissionhierarchy.repository.RoleRepository;
import com.cleveron.permissionhierarchy.modal.Role;
import com.cleveron.permissionhierarchy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Long id,Role role) {
        if (roleRepository.findById(id).isPresent()) {
            role.setId(id);
            return roleRepository.save(role);
        }

        return new Role();
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);

    }
}

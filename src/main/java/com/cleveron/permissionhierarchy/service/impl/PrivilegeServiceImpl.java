package com.cleveron.permissionhierarchy.service.impl;

import com.cleveron.permissionhierarchy.repository.PrivilegeRepository;
import com.cleveron.permissionhierarchy.modal.Privilege;
import com.cleveron.permissionhierarchy.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    public List<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    @Override
    public Optional<Privilege> findById(Long id) {
        return privilegeRepository.findById(id);
    }

    @Override
    public Privilege save(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public Privilege update(Long id, Privilege privilege) {
        if (privilegeRepository.findById(id).isPresent()) {
            privilege.setId(id);
            return privilegeRepository.save(privilege);
        }

        return new Privilege();
    }

    @Override
    public void delete(Long id) {
        privilegeRepository.deleteById(id);

    }
}

package com.cleveron.permissionhierarchy.service;

import com.cleveron.permissionhierarchy.modal.Privilege;

import java.util.List;
import java.util.Optional;

public interface PrivilegeService {
    List<Privilege> findAll();

    Optional<Privilege> findById(Long id);

    Privilege save(Privilege privilege);

    Privilege update(Long id, Privilege privilege);

    void delete(Long id);
}

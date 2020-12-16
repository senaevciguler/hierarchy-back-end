package com.cleveron.permissionhierarchy.repository;

import com.cleveron.permissionhierarchy.modal.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}

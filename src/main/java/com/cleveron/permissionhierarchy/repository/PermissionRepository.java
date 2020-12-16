package com.cleveron.permissionhierarchy.repository;

import com.cleveron.permissionhierarchy.modal.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}

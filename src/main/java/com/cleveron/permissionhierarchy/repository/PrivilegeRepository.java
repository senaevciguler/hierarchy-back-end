package com.cleveron.permissionhierarchy.repository;

import com.cleveron.permissionhierarchy.modal.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
}

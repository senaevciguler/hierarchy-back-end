package com.cleveron.permissionhierarchy.repository;

import com.cleveron.permissionhierarchy.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}




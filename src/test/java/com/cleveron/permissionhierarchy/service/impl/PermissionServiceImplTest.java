package com.cleveron.permissionhierarchy.service.impl;

import com.cleveron.permissionhierarchy.modal.Permission;
import com.cleveron.permissionhierarchy.repository.PermissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atMost;

@ExtendWith(MockitoExtension.class)
class PermissionServiceImplTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl service;

    @Test
    void getAll() {
        //given
        Permission permission = new Permission();
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        given(permissionRepository.findAll()).willReturn(permissions);

        //when
        List<Permission> foundPermissions = service.findAll();

        //then
        then(permissionRepository).should().findAll();
        assertThat(foundPermissions).hasSize(1);
    }

    @Test
    void findById() {
        //given
        Permission permission = new Permission();
        given(permissionRepository.findById(anyLong())).willReturn(Optional.of(permission));

        //when
        Optional<Permission> foundPermission = service.findById(1L);

        //then
        then(permissionRepository).should().findById(anyLong());
        assertThat(foundPermission).isNotNull();
    }

    @Test
    void create() {
        //given
        Permission permission = new Permission();
        given(permissionRepository.save(any(Permission.class))).willReturn(permission);

        //when
        Permission savedPermission = service.save(new Permission());

        //then
        then(permissionRepository).should().save(any(Permission.class));
        assertThat(savedPermission).isNotNull();
    }

    @Test
    void update() {
        //given
        Permission permission = new Permission();
        given(permissionRepository.findById(anyLong())).willReturn(Optional.of(permission));
        given(permissionRepository.save(any(Permission.class))).willReturn(permission);

        //when
        Permission savedPermission = service.update(1L, new Permission());

        //then
        then(permissionRepository).should().save(any(Permission.class));
        assertThat(savedPermission).isNotNull();
    }

    @Test
    void delete() {
        //when
        service.delete(anyLong());

        //then
        then(permissionRepository).should(atMost(2)).deleteById(anyLong());
    }
}
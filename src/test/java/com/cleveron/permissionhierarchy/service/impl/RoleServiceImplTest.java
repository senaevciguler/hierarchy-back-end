package com.cleveron.permissionhierarchy.service.impl;

import com.cleveron.permissionhierarchy.modal.Role;
import com.cleveron.permissionhierarchy.repository.RoleRepository;
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
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl service;

    @Test
    void getAll() {
        //given
        Role role = new Role();
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        given(roleRepository.findAll()).willReturn(roles);

        //when
        List<Role> foundRoles = service.findAll();

        //then
        then(roleRepository).should().findAll();
        assertThat(foundRoles).hasSize(1);
    }

    @Test
    void findById() {
        //given
        Role role = new Role();
        given(roleRepository.findById(anyLong())).willReturn(Optional.of(role));

        //when
        Optional<Role> foundRole = service.findById(1L);

        //then
        then(roleRepository).should().findById(anyLong());
        assertThat(foundRole).isNotNull();
    }

    @Test
    void create() {
        //given
        Role role = new Role();
        given(roleRepository.save(any(Role.class))).willReturn(role);

        //when
        Role savedRole = service.save(new Role());

        //then
        then(roleRepository).should().save(any(Role.class));
        assertThat(savedRole).isNotNull();
    }

    @Test
    void update() {
        //given
        Role role = new Role();
        given(roleRepository.findById(anyLong())).willReturn(Optional.of(role));
        given(roleRepository.save(any(Role.class))).willReturn(role);

        //when
        Role savedRole = service.update(1L, new Role());

        //then
        then(roleRepository).should().save(any(Role.class));
        assertThat(savedRole).isNotNull();
    }

    @Test
    void delete() {
        //when
        service.delete(anyLong());

        //then
        then(roleRepository).should(atMost(2)).deleteById(anyLong());
    }
}
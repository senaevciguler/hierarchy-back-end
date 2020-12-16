package com.cleveron.permissionhierarchy.service.impl;

import com.cleveron.permissionhierarchy.modal.Privilege;
import com.cleveron.permissionhierarchy.repository.PrivilegeRepository;
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
class PrivilegeServiceImplTest {

    @Mock
    private PrivilegeRepository privilegeRepository;

    @InjectMocks
    private PrivilegeServiceImpl service;

    @Test
    void getAll() {
        //given
        Privilege privilege = new Privilege();
        List<Privilege> privileges = new ArrayList<>();
        privileges.add(privilege);
        given(privilegeRepository.findAll()).willReturn(privileges);

        //when
        List<Privilege> foundPrivileges = service.findAll();

        //then
        then(privilegeRepository).should().findAll();
        assertThat(foundPrivileges).hasSize(1);
    }

    @Test
    void findById() {
        //given
        Privilege privilege = new Privilege();
        given(privilegeRepository.findById(anyLong())).willReturn(Optional.of(privilege));

        //when
        Optional<Privilege> foundPrivilege = service.findById(1L);

        //then
        then(privilegeRepository).should().findById(anyLong());
        assertThat(foundPrivilege).isNotNull();
    }

    @Test
    void create() {
        //given
        Privilege privilege = new Privilege();
        given(privilegeRepository.save(any(Privilege.class))).willReturn(privilege);

        //when
        Privilege savedPrivilege = service.save(new Privilege());

        //then
        then(privilegeRepository).should().save(any(Privilege.class));
        assertThat(savedPrivilege).isNotNull();
    }

    @Test
    void update() {
        //given
        Privilege privilege = new Privilege();
        given(privilegeRepository.findById(anyLong())).willReturn(Optional.of(privilege));
        given(privilegeRepository.save(any(Privilege.class))).willReturn(privilege);

        //when
        Privilege savedPrivilege = service.update(1L, new Privilege());

        //then
        then(privilegeRepository).should().save(any(Privilege.class));
        assertThat(savedPrivilege).isNotNull();
    }

    @Test
    void delete() {
        //when
        service.delete(anyLong());

        //then
        then(privilegeRepository).should(atMost(2)).deleteById(anyLong());
    }
}
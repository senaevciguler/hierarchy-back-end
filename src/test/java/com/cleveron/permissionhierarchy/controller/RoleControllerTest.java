package com.cleveron.permissionhierarchy.controller;

import com.cleveron.permissionhierarchy.modal.Role;
import com.cleveron.permissionhierarchy.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    @Mock
    RoleService roleService;

    @InjectMocks
    RoleController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        //given
        given(roleService.findAll()).willReturn(List.of(new Role()));

        //when
        mockMvc.perform(get("/api/v1/roles"))
                .andExpect(status().isOk());

        //then
        then(roleService).should().findAll();
    }

    @Test
    void findById() throws Exception {
        //given
        given(roleService.findById(1L)).willReturn(Optional.of(new Role()));

        //when
        mockMvc.perform(get("/api/v1/roles/{id}", "1"))
                .andExpect(status().isOk());

        //then
        then(roleService).should().findById(1L);
    }

    @Test
    void save() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Role.builder().id(1L).build());
        given(roleService.save(any())).willReturn(new Role());

        //when
        mockMvc.perform(post("/api/v1/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        //then
        then(roleService).should().save(any());
    }

    @Test
    void update() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Role.builder().name("test").build());
        given(roleService.update(1L, Role.builder().name("test").build())).willReturn(new Role());

        //when
        mockMvc.perform(put("/api/v1/roles/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        //then
        then(roleService).should().update(1L, Role.builder().name("test").build());
    }

    @Test
    void deleteRole() throws Exception {
        mockMvc.perform(delete("/api/v1/roles/{id}", 1L)).andExpect(status().isOk());
    }
}
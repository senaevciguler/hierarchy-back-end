package com.cleveron.permissionhierarchy.controller;

import com.cleveron.permissionhierarchy.modal.Permission;
import com.cleveron.permissionhierarchy.service.PermissionService;
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
class PermissionControllerTest {

    @Mock
    PermissionService permissionService;

    @InjectMocks
    PermissionController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        //given
        given(permissionService.findAll()).willReturn(List.of(new Permission()));

        //when
        mockMvc.perform(get("/api/v1/permissions"))
                .andExpect(status().isOk());

        //then
        then(permissionService).should().findAll();
    }

    @Test
    void findById() throws Exception {
        //given
        given(permissionService.findById(1L)).willReturn(Optional.of(new Permission()));

        //when
        mockMvc.perform(get("/api/v1/permissions/{id}", "1"))
                .andExpect(status().isOk());

        //then
        then(permissionService).should().findById(1L);
    }

    @Test
    void save() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Permission.builder().id(1L).build());
        given(permissionService.save(any())).willReturn(new Permission());

        //when
        mockMvc.perform(post("/api/v1/permissions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        //then
        then(permissionService).should().save(any());
    }

    @Test
    void update() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Permission.builder().name("test").build());
        given(permissionService.update(1L, Permission.builder().name("test").build())).willReturn(new Permission());

        //when
        mockMvc.perform(put("/api/v1/permissions/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        //then
        then(permissionService).should().update(1L, Permission.builder().name("test").build());
    }

    @Test
    void deletePermission() throws Exception {
        mockMvc.perform(delete("/api/v1/permissions/{id}", 1L)).andExpect(status().isOk());
    }
}
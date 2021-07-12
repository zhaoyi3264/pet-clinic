package com.zzy.petclinic.controller;

import com.zzy.petclinic.model.Owner;
import com.zzy.petclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private final static int OWNER_ID = 1;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void processCreationFormSuccess() throws Exception {
        MockHttpServletRequestBuilder post = post("/owners/new")
                .param("id", String.valueOf(OWNER_ID))
                .param("firstName", "Joe")
                .param("lastName", "Bloggs")
                .param("address", "123 Caramel Street")
                .param("city", "London")
                .param("telephone", "01316761638");
        mockMvc.perform(post)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/null"));
        verify(ownerService).save(isA(Owner.class));
    }

    @Test
    void processCreationFormFail() throws Exception {
        mockMvc.perform(post("/owners/new").param("name", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void initFindForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void processFindForm() throws Exception {
        when(ownerService.findByLastNameLike(anyString())).thenReturn(Collections.singletonList(new Owner()));
        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/null"));
        verify(ownerService).findByLastNameLike(isA(String.class));
    }

    @Test
    void showOwner() throws Exception {
        when(ownerService.findById(OWNER_ID)).thenReturn(new Owner());
        mockMvc.perform(get("/owners/{ownerId}", OWNER_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"));
        verify(ownerService).findById(anyInt());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findById(OWNER_ID)).thenReturn(new Owner());
        mockMvc.perform(get("/owners/{ownerId}/edit", OWNER_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
        verify(ownerService).findById(anyInt());
    }

    @Test
    void processUpdateOwnerFormSuccess() throws Exception {
        MockHttpServletRequestBuilder post = post("/owners/{ownerId}/edit", OWNER_ID)
                .param("id", String.valueOf(OWNER_ID))
                .param("firstName", "Joe")
                .param("lastName", "Bloggs")
                .param("address", "123 Caramel Street")
                .param("city", "London")
                .param("telephone", "01316761638");
        mockMvc.perform(post)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
        verify(ownerService).save(isA(Owner.class));
    }

    @Test
    void processUpdateOwnerFormFail() throws Exception {
        MockHttpServletRequestBuilder post = post("/owners/{ownerId}/edit", OWNER_ID)
                .param("id", String.valueOf(OWNER_ID))
                .param("telephone", "123");
        mockMvc.perform(post)
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
        verify(ownerService, times(0)).save(isA(Owner.class));
    }
}
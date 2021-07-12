package com.zzy.petclinic.controller;

import com.zzy.petclinic.model.Owner;
import com.zzy.petclinic.model.Pet;
import com.zzy.petclinic.service.OwnerService;
import com.zzy.petclinic.service.PetService;
import com.zzy.petclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    private static final int PET_ID = 1;

    @Mock
    private PetService petService;

    @Mock
    private PetTypeService petTypeService;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private PetController petController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
        when(ownerService.findById(anyInt())).thenReturn(new Owner());
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/new", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        MockHttpServletRequestBuilder post = post("/owners/{ownerId}/pets/new", 1)
                .param("id", String.valueOf(PET_ID))
                .param("name", "George")
                .param("birthDate", "2020-12-12");
        mockMvc.perform(post)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
        verify(petService).save(isA(Pet.class));
    }

    @Test
    void initUpdateForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit", 1, PET_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"));
        verify(petService).findById(anyInt());
    }

    @Test
    void processUpdateFormSuccess() throws Exception {
        MockHttpServletRequestBuilder post = post("/owners/{ownerId}/pets/{petId}/edit", 1, PET_ID)
                .param("id", String.valueOf(PET_ID))
                .param("name", "George")
                .param("birthDate", "2020-12-12");
        mockMvc.perform(post)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    void processUpdateForFail() throws Exception {
        MockHttpServletRequestBuilder post = post("/owners/{ownerId}/pets/{petId}/edit", 1, PET_ID)
                .param("id", String.valueOf(PET_ID))
                .param("name", "George")
                .param("birthDate", "wrong format");
        mockMvc.perform(post)
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }
}
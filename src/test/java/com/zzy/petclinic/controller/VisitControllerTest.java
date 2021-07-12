package com.zzy.petclinic.controller;

import com.zzy.petclinic.model.Pet;
import com.zzy.petclinic.model.Visit;
import com.zzy.petclinic.service.PetService;
import com.zzy.petclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private final static int PET_ID = 1;

    @Mock
    private VisitService visitService;

    @Mock
    private PetService petService;

    @InjectMocks
    private VisitController visitController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
        when(petService.findById(anyInt())).thenReturn(new Pet());
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get("/owners/*/pets/{petId}/visits/new", PET_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
        verify(petService).findById(eq(PET_ID));
    }

    @Test
    void processNewVisitFormSuccess() throws Exception {
        MockHttpServletRequestBuilder post = post("/owners/*}/pets/{petId}/visits/new", PET_ID)
                .param("description", "description");
        mockMvc.perform(post)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        verify(petService).findById(eq(PET_ID));
        verify(visitService).save(isA(Visit.class));
    }

    @Test
    void processNewVisitFormFail() throws Exception {
        MockHttpServletRequestBuilder post = post("/owners/*}/pets/{petId}/visits/new", PET_ID);
        mockMvc.perform(post)
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
        verify(petService).findById(eq(PET_ID));
    }
}
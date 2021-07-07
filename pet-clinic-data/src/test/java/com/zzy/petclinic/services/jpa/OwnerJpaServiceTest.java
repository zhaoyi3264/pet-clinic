package com.zzy.petclinic.services.jpa;

import com.zzy.petclinic.model.Owner;
import com.zzy.petclinic.repository.OwnerRepository;
import com.zzy.petclinic.repository.PetRepository;
import com.zzy.petclinic.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    String lastName = "Smith";
    Owner returnOwner;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(lastName).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(1L).build());
        returnOwnerSet.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        Set<Owner> owners = ownerJpaService.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = ownerJpaService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner saved = ownerJpaService.save(owner);
        assertNotNull(saved);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerJpaService.delete(returnOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner owner = ownerJpaService.findByLastName(lastName);
        assertEquals(lastName, owner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }
}
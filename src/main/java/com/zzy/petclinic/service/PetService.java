package com.zzy.petclinic.service;

import com.zzy.petclinic.model.Pet;
import com.zzy.petclinic.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService extends AbstractService<Pet, Integer, PetRepository> {

    public PetService(PetRepository repository) {
        super(repository);
    }
}

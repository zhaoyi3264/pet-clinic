package com.zzy.petclinic.service;

import com.zzy.petclinic.model.PetType;
import com.zzy.petclinic.repository.PetTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PetTypeService extends AbstractService<PetType, Integer, PetTypeRepository> {

    public PetTypeService(PetTypeRepository repository) {
        super(repository);
    }
}

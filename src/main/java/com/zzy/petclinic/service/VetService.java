package com.zzy.petclinic.service;

import com.zzy.petclinic.model.Vet;
import com.zzy.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;

@Service
public class VetService extends AbstractService<Vet, Integer, VetRepository> {

    public VetService(VetRepository repository) {
        super(repository);
    }
}

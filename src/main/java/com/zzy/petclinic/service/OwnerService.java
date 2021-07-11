package com.zzy.petclinic.service;

import com.zzy.petclinic.model.Owner;
import com.zzy.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OwnerService extends AbstractService<Owner, Integer, OwnerRepository> {

    public OwnerService(OwnerRepository repository) {
        super(repository);
    }

    public Collection<Owner> findByLastNameLike(String lastName) {
        return this.repository.findByLastNameLike(lastName);
    }
}

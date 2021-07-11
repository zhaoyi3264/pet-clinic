package com.zzy.petclinic.service;

import com.zzy.petclinic.model.Visit;
import com.zzy.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService extends AbstractService<Visit, Integer, VisitRepository> {

    public VisitService(VisitRepository repository) {
        super(repository);
    }

    public List<Visit> findByPetId(Integer petId) {
        return this.repository.findByPetId(petId);
    }
}

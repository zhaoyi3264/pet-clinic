package com.zzy.petclinic.repository;

import com.zzy.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Visit, Long> {
}

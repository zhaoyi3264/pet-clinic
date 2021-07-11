package com.zzy.petclinic.repository;

import com.zzy.petclinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {

}

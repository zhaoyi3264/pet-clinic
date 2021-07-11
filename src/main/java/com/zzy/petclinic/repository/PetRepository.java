package com.zzy.petclinic.repository;

import com.zzy.petclinic.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

}

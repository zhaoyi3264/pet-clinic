package com.zzy.petclinic.repository;

import com.zzy.petclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    Collection<Owner> findByLastNameLike(String lastName);
}

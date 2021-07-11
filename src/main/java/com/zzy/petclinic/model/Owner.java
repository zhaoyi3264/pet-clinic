package com.zzy.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @NotEmpty
    @Column(name = "address")
    private String address;

    @NotEmpty
    @Column(name = "city")
    private String city;

    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public void addPet(Pet pet) {
        if (pet.isNew()) {
            this.pets.add(pet);
        }
        pet.setOwner(this);
    }

    public Pet getPet(String name) {
        return this.pets.stream()
                .filter(pet -> !pet.isNew() && pet.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

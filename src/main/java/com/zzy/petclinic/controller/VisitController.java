package com.zzy.petclinic.controller;

import com.zzy.petclinic.model.Pet;
import com.zzy.petclinic.model.Visit;
import com.zzy.petclinic.service.PetService;
import com.zzy.petclinic.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") int petId, ModelMap model) {
        Pet pet = this.petService.findById(petId);
        List<Visit> visits = this.visitService.findByPetId(petId);
        pet.setVisits(new HashSet<>(visits));
        Visit visit = new Visit();
        pet.addVisit(visit);
        model.put("pet", pet);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm() {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            this.visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}

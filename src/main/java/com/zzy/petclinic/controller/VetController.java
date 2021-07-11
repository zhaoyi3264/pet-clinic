package com.zzy.petclinic.controller;

import com.zzy.petclinic.model.Vet;
import com.zzy.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/vets")
    public String showVetList(ModelMap model) {
        Collection<Vet> vets = this.vetService.findAll();
        model.put("vets", vets);
        return "vets/vetList";
    }
}

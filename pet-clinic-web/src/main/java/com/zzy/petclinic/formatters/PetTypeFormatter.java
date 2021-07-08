package com.zzy.petclinic.formatters;

import com.zzy.petclinic.model.PetType;
import com.zzy.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Set;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Set<PetType> petTypes = petTypeService.findAll();
        for (PetType type : petTypes) {
            if (type.getName().equals(s)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + s, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}

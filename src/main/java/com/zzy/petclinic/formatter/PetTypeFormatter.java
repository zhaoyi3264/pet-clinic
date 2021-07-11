package com.zzy.petclinic.formatter;

import com.zzy.petclinic.model.PetType;
import com.zzy.petclinic.service.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

// TODO: read about this
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        return this.petTypeService.findAll().stream()
                .filter(type -> type.getName().equals(text))
                .findFirst()
                .orElseThrow(() -> new ParseException("type not found: " + text, 0));
    }
}
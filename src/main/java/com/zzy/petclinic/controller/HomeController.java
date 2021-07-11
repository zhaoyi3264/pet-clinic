package com.zzy.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/oups")
    public String triggerException() {
        throw new RuntimeException("Expected: controller used to showcase what " + "happens when an exception is thrown");
    }
}

package com.idovia.jus_de_bagarre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueController {

    @GetMapping("/")
    public String displayHome (Model model) {
        model.addAttribute("user");
        return "index";
    }

    @GetMapping("/login")
    public String displayLogin () {
        return "login";
    }

    @GetMapping("/accueil")
    public String displayAccueil () {
        return "index";
    }
}

package com.idovia.jus_de_bagarre.controller.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.idovia.jus_de_bagarre.service.RecipeService;

@Controller
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/comment/ajouter")
    public String addComment (Model model, @RequestParam("name") String name, @RequestParam("text") String text, @RequestParam("recipeId") String recipeId) {
        if (recipeService.addComment(name, text, recipeId)) {
            return "redirect:/cocktails";
        }
        return "redirect:/error";
    }

}

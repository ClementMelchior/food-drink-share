package com.idovia.jus_de_bagarre.error;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idovia.jus_de_bagarre.model.recipe.RecipeModel;

public class RecipeError {

    public RecipeError () {

    }

    public String init (RedirectAttributes ra, RecipeModel recipe, String error) {
        switch(error){
            case "empty": 
                return this.initEmpty(ra, recipe);
            case "grade":
                return this.initGrade(ra, recipe);
            case "picture":
                return this.initPicture(ra, recipe);
            default:
                return this.initError();
        }        

    }

    private String initEmpty (RedirectAttributes ra, RecipeModel recipe) {
        ra.addFlashAttribute("error", "Bon frero, concentre t'as pas tout complété. Après si c'est un bug, dis le à mon créateur il a encore dû faire une connerie...");
        ra.addFlashAttribute("recipe", recipe);
        return "redirect:/vin/ajouter";
    }

    private String initGrade (RedirectAttributes ra, RecipeModel recipe) {
        ra.addFlashAttribute("error", "Bourico, tu le fais expres ou quoi ? La note c'est entre 0 et 5, pas plus, pas moins.");
        ra.addFlashAttribute("recipe", recipe);
        return "redirect:/vin/ajouter";
    }

    private String initPicture (RedirectAttributes ra, RecipeModel recipe) {
        ra.addFlashAttribute("error", "Ohhhhhhhh la photo ! T'as oublié !!");
        ra.addFlashAttribute("recipe", recipe);
        return "redirect:/vin/ajouter";
    }

    private String initError () {
        return "redirect:/error";
    }
    
}

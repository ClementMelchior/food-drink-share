package com.idovia.jus_de_bagarre.error;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.idovia.jus_de_bagarre.model.wine.WineModel;

public class WineError {

    public WineError () {

    }

    public String init (RedirectAttributes ra, WineModel wine, String error) {
        switch(error){
            case "empty": 
                return this.initEmpty(ra, wine);
            case "grade":
                return this.initGrade(ra, wine);
            case "characteristic":
                return this.initCharacteristic(ra, wine);
            case "picture":
                return this.initPicture(ra, wine);
            default:
                return this.initError();
        }        

    }

    private String initEmpty (RedirectAttributes ra, WineModel wine) {
        ra.addFlashAttribute("error", "Bon frero, concentre t'as pas tout complété. Après si c'est un bug, dis le à mon créateur il a encore dû faire une connerie...");
        ra.addFlashAttribute("wine", wine);
        return "redirect:/vin/ajouter";
    }

    private String initGrade (RedirectAttributes ra, WineModel wine) {
        ra.addFlashAttribute("error", "Bourico, tu le fais expres ou quoi ? La note c'est entre 0 et 5, pas plus, pas moins.");
        ra.addFlashAttribute("wine", wine);
        return "redirect:/vin/ajouter";
    }

    private String initCharacteristic (RedirectAttributes ra, WineModel wine) {
        ra.addFlashAttribute("error", "PAF ! Raté, faut que tu selectionne toutes les caractéristiques de ton bon vin !");
        ra.addFlashAttribute("wine", wine);
        return "redirect:/vin/ajouter";
    }

    private String initPicture (RedirectAttributes ra, WineModel wine) {
        ra.addFlashAttribute("error", "Ohhhhhhhh la photo ! T'as oublié !!");
        ra.addFlashAttribute("wine", wine);
        return "redirect:/vin/ajouter";
    }

    private String initError () {
        return "redirect:/error";
    }
    
}

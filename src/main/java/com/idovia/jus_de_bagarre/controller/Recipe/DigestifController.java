package com.idovia.jus_de_bagarre.controller.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idovia.jus_de_bagarre.model.recipe.RecipeModel;
import com.idovia.jus_de_bagarre.service.RecipeService;

@Controller
public class DigestifController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/digestifs")
    public String displayDigestif (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getName());
        model.addAttribute("recipes", recipeService.findAllByCategory("Digestif"));
        return "digestif";
    }

    @GetMapping("/digestif/ajouter")
    public String displayNewDigestif (Model model) {
        RecipeModel recipe =  new RecipeModel();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        recipe.setOwner(auth.getName());
        recipe.setCategory("Digestif");
        model.addAttribute("way", "digestifs");
        model.addAttribute("recipe", recipe);
        return "new_recipe";
    }

    @PostMapping("/digestif/ajouter")
    public String createCocktail (RedirectAttributes ra, @ModelAttribute RecipeModel recipe, @RequestParam("image") MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        recipe.setOwner(auth.getName());
        recipe.setCategory("Digestif");

        // check if all field are filled 
        if (recipe.isNotNull() && recipe.isNotEmpty()) {

            // check if grade is < 0 and grade > 5)
            if (recipe.isGradeCorrect()) {


                // check if picture is added
                if (!file.isEmpty()) {

                    if (recipeService.save(recipe, file)) {
                        return "redirect:/digestifs";
                    }
                    return "redirect:/error";
                }
                ra.addFlashAttribute("error", "Ohhhhhhhh la photo ! T'as oublié !!");
                ra.addFlashAttribute("recipe", recipe);
                return "redirect:/digestif/ajouter";
            }
            ra.addFlashAttribute("error", "Bourico, tu le fais expres ou quoi ? La note c'est entre 0 et 5, pas plus, pas moins.");
            ra.addFlashAttribute("recipe", recipe);
            return "redirect:/digestif/ajouter";
        }
        ra.addFlashAttribute("error", "Bon frero, concentre toi, t'as pas tout complété. Après si c'est un bug, dis le à mon créateur il a encore dû faire une connerie...");
        ra.addFlashAttribute("recipe", recipe);
        return "redirect:/digestif/ajouter";
    }    
}
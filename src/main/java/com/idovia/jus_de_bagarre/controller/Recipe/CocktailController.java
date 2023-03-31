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
import com.idovia.jus_de_bagarre.error.RecipeError;
import com.idovia.jus_de_bagarre.model.recipe.RecipeModel;
import com.idovia.jus_de_bagarre.service.*;

@Controller
public class CocktailController {

    @Autowired
    private RecipeService recipeService;


    @GetMapping("/cocktails")
    public String displayCocktail (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getName());
        model.addAttribute("recipes", recipeService.findAllByCategory("Cocktail"));
        return "cocktail";
    }

    // ----------- Ajout -----------

    @GetMapping("/cocktail/ajouter")
    public String displayNewCocktail (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("way", "cocktail");
        if (model.getAttribute("recipe")!=null) {
            return "new_recipe";
        }
        RecipeModel recipe =  new RecipeModel();
        recipe.setOwner(auth.getName());
        recipe.setCategory("Cocktail");
        model.addAttribute("recipe", recipe);
        return "new_recipe";
    }


    @PostMapping("/cocktail/ajouter")
    public String createCocktail (RedirectAttributes ra, @ModelAttribute RecipeModel recipe, @RequestParam("image") MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        recipe.setOwner(auth.getName());
        recipe.setCategory("Cocktail");

        // check if all field are filled 
        if (recipe.isNotNull() && recipe.isNotEmpty()) {

            // check if grade is < 0 and grade > 5)
            if (recipe.isGradeCorrect()) {


                // check if picture is added
                if (!file.isEmpty()) {

                    if (recipeService.save(recipe, file)) {
                        return "redirect:/cocktails";
                    }
                    return "redirect:/error";
                }
                return new RecipeError().init(ra, recipe, "picture");
            }
            return new RecipeError().init(ra, recipe, "grade");
        }
        return new RecipeError().init(ra, recipe, "empty");
    }

    // http://localhost:80/cocktail/delete?name=" + name + "&text=" + text + "&recipeId=" + recipeId
    // http://jusdebagarre/cocktail/delete?name=" + name + "&text=" + text + "&recipeId=" + recipeId
    @GetMapping("/cocktail/delete/")
    public String deleteCocktail (@RequestParam("name") String name, @RequestParam("recipeId") String recipeId) {
        if (name.equals("admin")) {
            if (recipeService.deleteRecipe(recipeId)) {
                return "Delete done";
            }
        }
        return "ERROR";
    }


    /*
     * GESTION 
     */

     @GetMapping("/manager/cocktail")
     public String cocktailManager (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("admin"))
        model.addAttribute("user", auth.getName());
        model.addAttribute("recipes", recipeService.findAllByCategory("Cocktail"));
        return "manager/cocktail";
     }
}

package com.idovia.jus_de_bagarre.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.idovia.jus_de_bagarre.api.model.recipe.RecipeModel;
import com.idovia.jus_de_bagarre.api.repository.RecipeRepository;

public class CocktailController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/cocktails")
    public List<RecipeModel> getWines () { 
        return recipeRepository.findAll();
    }

    @PostMapping("/cocktail")
    public boolean createWine (@RequestBody RecipeModel recipeModel) { 
        recipeRepository.save(recipeModel);
        return true;
    }

    @DeleteMapping("/cocktail/{id}")
    public boolean deleteWine (@PathVariable Long id) { 
        recipeRepository.deleteById(id);
        return true;
    }
    
}

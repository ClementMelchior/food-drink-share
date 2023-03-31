package com.idovia.jus_de_bagarre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idovia.jus_de_bagarre.model.recipe.RecipeModel;

public interface RecipeRepository extends JpaRepository<RecipeModel, String>{

    public List <RecipeModel> findAllByCategory(String category);

}

package com.idovia.jus_de_bagarre.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idovia.jus_de_bagarre.model.recipe.CommentRecipeModel;

public interface CommentRecipeRepository extends JpaRepository<CommentRecipeModel, String>{

    public List <CommentRecipeModel> findAllByRecipeId(String id);

}

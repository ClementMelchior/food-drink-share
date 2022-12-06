package com.idovia.jus_de_bagarre.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.idovia.jus_de_bagarre.api.model.recipe.RecipeModel;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel, Long>{

}

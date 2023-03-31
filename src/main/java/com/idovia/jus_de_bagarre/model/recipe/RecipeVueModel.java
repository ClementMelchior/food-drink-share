package com.idovia.jus_de_bagarre.model.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecipeVueModel {
    private String recipeId;    
    private String category;
    private String name;
    private String description;
    private String owner;
    private List <String> ingredients;
    private String instructions;
    private List<CommentRecipeModel> comments = new ArrayList<>(); 
    private int grade;
    private String picture;


    public RecipeVueModel(RecipeModel recipeModel, ArrayList <CommentRecipeModel> comments) {
        this.recipeId = recipeModel.getRecipeId();
        this.category = recipeModel.getCategory();
        this.name = recipeModel.getName();
        this.description = recipeModel.getDescription();
        this.owner = recipeModel.getOwner();
        this.ingredients = Arrays.asList(recipeModel.getIngredients().split(", "));
        this.instructions = recipeModel.getInstructions();
        this.comments = comments;
        this.grade = recipeModel.getGrade();
        this.picture = recipeModel.getPicture();
    }
    
    
}

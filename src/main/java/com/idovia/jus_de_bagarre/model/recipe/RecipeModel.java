package com.idovia.jus_de_bagarre.model.recipe;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Recipe")
@Getter @Setter
public class RecipeModel {
    @Id
    private String recipeId;    
    private String category;
    private String name;
    @Column(columnDefinition="TEXT")
    private String description;
    private String owner;
    @Column(columnDefinition="TEXT")
    private String ingredients;
    @Column(columnDefinition="TEXT")
    private String instructions;
    private int grade;
    private String picture;


    public RecipeModel(String id, String category, String name, String description, String owner, String ingredients,
            String instructions, int grade) {
        this.recipeId=id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.grade = grade;
    }

    public RecipeModel () {
        this.recipeId=UUID.randomUUID().toString();
    }

    public boolean isNotNull() {
        if (name==null || description==null || owner==null || ingredients==null || instructions==null) {
            System.out.println("Recipe null");
            return false;
        }
        return true;
    }

    public boolean isNotEmpty() {
        System.out.println(this);
        if (name.equals("") || description.equals("") || owner.equals("") || ingredients.equals("") || instructions.equals("")) {
            System.out.println("Recipe empty");
            return false;
        }
        return true;
    }

    public boolean isGradeCorrect() {
        if (grade < 0 || grade > 5) {
            System.out.println("Recipe grade incorrect");
            return false;
        }
        return true;
    }

    public String toString () {
        return "RECIPE : " + recipeId + " | " + category + " | " + name + " | " + description + " | " + owner
            + " | " + ingredients + " | " + instructions + " | " + grade;
    }




}
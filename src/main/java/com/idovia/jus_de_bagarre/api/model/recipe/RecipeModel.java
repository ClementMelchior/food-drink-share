package com.idovia.jus_de_bagarre.api.model.recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class RecipeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String category;
    private String name;
    private String owner;
    private String ingredients;
    private String comment; 
    private int grade;
    private String picture;


    public RecipeModel(Long id, String category, String name, String owner, String ingredients, String comment,
            int grade, String picture) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.owner = owner;
        this.ingredients = ingredients;
        this.comment = comment;
        this.grade = grade;
        this.picture = picture;
    }

}
package com.idovia.jus_de_bagarre.model.recipe;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CommentRecipe")
@Getter @Setter @NoArgsConstructor
public class CommentRecipeModel {

    @Id
    private String commentRecipeId; 
    private String name;
    private String text;
    private String recipeId;

    public CommentRecipeModel (String name, String text, String recipeId) {
        this.commentRecipeId=UUID.randomUUID().toString();
        this.name=name;
        this.text=text;
        this.recipeId=recipeId;
    }
}

package com.idovia.jus_de_bagarre.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.idovia.jus_de_bagarre.model.recipe.CommentRecipeModel;
import com.idovia.jus_de_bagarre.model.recipe.RecipeModel;
import com.idovia.jus_de_bagarre.model.recipe.RecipeVueModel;
import com.idovia.jus_de_bagarre.repository.CommentRecipeRepository;
import com.idovia.jus_de_bagarre.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CommentRecipeRepository commentRecipeRepository;

    public static String UPLOAD_DIRECTORY_RECIPE = System.getProperty("user.dir") + "/src/main/resources/static/img/recipe";

    public List<RecipeVueModel> findAll () { 
        try {
            return recipeRepository.findAllByCategory("Cocktail").stream().map(r -> {
                ArrayList <CommentRecipeModel> comments = new ArrayList<CommentRecipeModel>();
                try {
                    comments.addAll(commentRecipeRepository.findAllByRecipeId(r.getRecipeId()));
                    System.out.println(comments.get(0).getText());
                } catch (Exception e) {
                    System.out.println("error " + r.getRecipeId());
                }
                return new RecipeVueModel(r, comments);
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList <RecipeVueModel> ();
        }
    }

    public Object findAllByCategory(String category) {
        try {
            return recipeRepository.findAllByCategory(category).stream().map(r -> {
                ArrayList <CommentRecipeModel> comments = new ArrayList<CommentRecipeModel>();
                try {
                    comments.addAll(commentRecipeRepository.findAllByRecipeId(r.getRecipeId()));
                    System.out.println(comments.get(0).getText());
                } catch (Exception e) {
                    System.out.println("error " + r.getRecipeId());
                }
                return new RecipeVueModel(r, comments);
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList <RecipeVueModel> ();
        }    
    }

    public boolean create (RecipeModel recipeModel) { 
        recipeRepository.save(recipeModel);
        return true;
    }

    public boolean save(RecipeModel recipe, MultipartFile file) {
        try {
            recipe.setPicture(recipe.getRecipeId()+"."+file.getOriginalFilename().split("\\.")[1]);
            savePicture(file, recipe.getPicture());
            recipeRepository.save(recipe);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void savePicture (MultipartFile file, String name) throws IOException {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY_RECIPE, name);
        Files.write(fileNameAndPath, file.getBytes());    
    }

    public boolean deleteRecipe (String id) { 
        Optional<RecipeModel> recipe = recipeRepository.findById(id);
        boolean isExecute = false;
        if (recipe.isPresent()) {
            recipeRepository.deleteById(id);
            try {
                deletePicture(recipe.get().getPicture());
                isExecute = true;
            } catch (Exception e) {
                e.printStackTrace();
                return isExecute;
            }
        }
        return isExecute;
    }

    private void deletePicture (String name) throws IOException {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY_RECIPE, name);
        Files.delete(fileNameAndPath);    
    }


    public boolean addComment(String name, String text, String recipeId) {
        if (!(text.equals("")) && text != null) {
            try {
                commentRecipeRepository.save(new CommentRecipeModel(name, text, recipeId));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    
}

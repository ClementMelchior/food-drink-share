package com.idovia.jus_de_bagarre.model.wine;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class WineVueModel {
    private String wineId;
    private String category;
    private String name;
    private String description;
    private String owner; 
    private int puissance;
    private int tanicite;
    private int sucrosite;
    private int fruite;
    private String picture; 
    private int grade;
    private List<CommentWineModel> comments = new ArrayList<>(); 


    public WineVueModel(WineModel wine, ArrayList <CommentWineModel> comments) {
        this.wineId = wine.getWineId();
        this.category = wine.getCategory();
        this.name = wine.getName();
        this.description = wine.getDescription();
        this.owner = wine.getOwner();
        this.comments = comments;
        this.puissance = wine.getPuissance();
        this.tanicite = wine.getTanicite();
        this.sucrosite = wine.getSucrosite();
        this.fruite = wine.getFruite();
        this.grade = wine.getGrade();
        this.picture = wine.getPicture();
    }
    
}

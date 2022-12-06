package com.idovia.jus_de_bagarre.api.model.wine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Setter @Getter @NoArgsConstructor
public class WineModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String wineCategory;
    private int grade;
    private String comment;

    
    public WineModel(Long id, String name, String wineCategory, int grade, String comment) {
        this.id = id;
        this.name = name;
        this.wineCategory = wineCategory;
        this.grade = grade;
        this.comment = comment;
    }

}

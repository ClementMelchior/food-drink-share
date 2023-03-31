package com.idovia.jus_de_bagarre.model.wine;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Wine")
@Setter @Getter
public class WineModel {
    @Id
    private String wineId;
    private String category;
    private String name;
    @Column(columnDefinition="TEXT")
    private String description;
    private String owner; 
    private int puissance;
    private int tanicite;
    private int sucrosite;
    private int fruite;
    private String picture; 
    private int grade;

    public WineModel () {
        this.wineId=UUID.randomUUID().toString();
    }

    public boolean isNotNull() {
        if (name==null || description==null || owner==null) {
            return false;
        }
        return true;
    }

    public boolean isNotEmpty() {
        if (name.equals("") || description.equals("") || owner.equals("")) {
            return false;
        }
        return true;
    }

    public boolean isGradeCorrect() {
        if (grade < 0 || grade > 5) {
            return false;
        }
        return true;
    }

    public boolean isCharacteristicsCorrect() {
        if ((sucrosite < 0 || sucrosite > 5) && (tanicite < 0 || tanicite > 5) && (puissance < 0 || puissance > 5) && (fruite < 0 || fruite > 5)) {
            return false;
        }
        return true;    
    }
}

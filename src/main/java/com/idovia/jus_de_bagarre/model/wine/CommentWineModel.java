package com.idovia.jus_de_bagarre.model.wine;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CommentWine")
@NoArgsConstructor @Getter @Setter
public class CommentWineModel {

    @Id
    private String commentWineId; 
    private String name;
    private String text;
    private String wineId;


    public CommentWineModel (String name, String text, String wineId) {
        this.commentWineId=UUID.randomUUID().toString();
        this.name=name;
        this.text=text;
        this.wineId=wineId;
    }
    
}

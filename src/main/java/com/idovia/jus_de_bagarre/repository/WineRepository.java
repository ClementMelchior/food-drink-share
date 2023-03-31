package com.idovia.jus_de_bagarre.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idovia.jus_de_bagarre.model.wine.WineModel;

public interface WineRepository extends JpaRepository<WineModel, String> {

    public List <WineModel> findAllByCategory (String category);
    
}

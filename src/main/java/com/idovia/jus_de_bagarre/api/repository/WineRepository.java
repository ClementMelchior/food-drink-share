package com.idovia.jus_de_bagarre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.idovia.jus_de_bagarre.api.model.wine.WineModel;

@Repository
public interface WineRepository extends JpaRepository<WineModel, Long> {
    
}

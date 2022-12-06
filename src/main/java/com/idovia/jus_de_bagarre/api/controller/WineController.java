package com.idovia.jus_de_bagarre.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.idovia.jus_de_bagarre.api.model.wine.WineModel;
import com.idovia.jus_de_bagarre.api.repository.WineRepository;


@RestController
public class WineController {

    @Autowired
    private WineRepository wineRepository;

    @GetMapping("/wines")
    public List<WineModel> getWines () { 
        return wineRepository.findAll();
    }

    @PostMapping("/wine")
    public boolean createWine (@RequestBody WineModel wineModel) { 
        wineRepository.save(wineModel);
        return true;
    }

    @DeleteMapping("/wine/{id}")
    public boolean deleteWine (@PathVariable Long id) { 
        wineRepository.deleteById(id);
        return true;
    }
}

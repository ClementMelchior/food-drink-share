package com.idovia.jus_de_bagarre.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.idovia.jus_de_bagarre.model.wine.CommentWineModel;
import com.idovia.jus_de_bagarre.model.wine.WineModel;
import com.idovia.jus_de_bagarre.model.wine.WineVueModel;
import com.idovia.jus_de_bagarre.repository.CommentWineRepository;
import com.idovia.jus_de_bagarre.repository.WineRepository;

@Service
public class WineService {

    @Autowired
    private WineRepository wineRepository;
    @Autowired
    private CommentWineRepository commentWineRepository;
    public static String UPLOAD_DIRECTORY_WINE = System.getProperty("user.dir") + "/src/main/resources/static/img/wine";

    public List<WineModel> findAll () { 
        try {
            return wineRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList <WineModel> ();
        }
    }

    public boolean create (WineModel recipeModel) { 
        wineRepository.save(recipeModel);
        return true;
    }

    public boolean delete (String id) { 
        wineRepository.deleteById(id);
        return true;
    }

    public List<WineVueModel> findAllByCategory(String category) {
        try {
            return wineRepository.findAllByCategory(category).stream().map(w -> {
                ArrayList <CommentWineModel> comments = new ArrayList<CommentWineModel>();
                try {
                    comments.addAll(commentWineRepository.findAllByWineId(w.getWineId()));
                } catch (Exception e) {
                    System.out.println("error " + w.getWineId());
                }
                return new WineVueModel(w, comments);
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList <WineVueModel> ();
        }    
    }

    public boolean save(WineModel wine, MultipartFile file) {
        try {
            wine.setPicture(wine.getWineId()+"."+file.getOriginalFilename().split("\\.")[1]);
            savePicture(file, wine.getWineId());
            wineRepository.save(wine);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void savePicture (MultipartFile file, String name) throws IOException {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY_WINE, name+"."+file.getOriginalFilename().split("\\.")[1]);
        Files.write(fileNameAndPath, file.getBytes());
    }

    public boolean addComment(String name, String text, String wineId) {
        if (!(text.equals("")) && text != null) {
            try {
                commentWineRepository.save(new CommentWineModel(name, text, wineId));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    
    
}

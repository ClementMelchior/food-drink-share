package com.idovia.jus_de_bagarre.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.idovia.jus_de_bagarre.model.wine.CommentWineModel;

public interface CommentWineRepository extends JpaRepository<CommentWineModel, String>{

    public List <CommentWineModel> findAllByWineId(String id);

}

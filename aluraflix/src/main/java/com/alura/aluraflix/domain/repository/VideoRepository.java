package com.alura.aluraflix.domain.repository;

import java.util.List;

import com.alura.aluraflix.api.model.CategoriaVideoModel;
import com.alura.aluraflix.domain.model.Categoria;
import com.alura.aluraflix.domain.model.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface VideoRepository extends JpaRepository<Video, Long> {
    
    List<Video> findByCategoria(Categoria categoria);

    List<Video> findByTituloContains(String titulo);

    @Query("SELECT " + 
           "  new com.alura.aluraflix.api.model.CategoriaVideoModel(c.titulo, count(v.id)) " +
           "  FROM Video v JOIN v.categoria c " + 
           " GROUP BY c.titulo " + 
           " ORDER BY 2 DESC ")
    List<CategoriaVideoModel> findAllVideosByCategoria();
}

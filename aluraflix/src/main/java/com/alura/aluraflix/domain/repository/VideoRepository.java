package com.alura.aluraflix.domain.repository;

import java.util.List;

import com.alura.aluraflix.domain.model.Categoria;
import com.alura.aluraflix.domain.model.Video;

import org.springframework.data.jpa.repository.JpaRepository;
public interface VideoRepository extends JpaRepository<Video, Long> {
    
    List<Video> findByCategoria(Categoria categoria);

    List<Video> findByTituloContains(String titulo);

}

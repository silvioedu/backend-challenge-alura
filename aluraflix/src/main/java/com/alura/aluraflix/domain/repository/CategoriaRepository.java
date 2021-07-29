package com.alura.aluraflix.domain.repository;

import com.alura.aluraflix.domain.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}

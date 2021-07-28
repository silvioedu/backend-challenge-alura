package com.alura.aluraflix.domain.repository;

import com.alura.aluraflix.domain.model.Video;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
    
}

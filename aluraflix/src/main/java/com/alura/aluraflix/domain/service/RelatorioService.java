package com.alura.aluraflix.domain.service;

import java.util.List;

import com.alura.aluraflix.api.model.CategoriaVideoModel;
import com.alura.aluraflix.domain.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private VideoRepository videoRepository;

    public List<CategoriaVideoModel> findAllVideosByCategoria() {
        return videoRepository.findAllVideosByCategoria();
    }
    
}

package com.alura.aluraflix.api.controller;

import java.util.List;

import com.alura.aluraflix.api.assembler.VideoModelAssembler;
import com.alura.aluraflix.api.model.VideoModel;
import com.alura.aluraflix.domain.model.Video;
import com.alura.aluraflix.domain.repository.VideoRepository;
import com.alura.aluraflix.domain.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias/{id}/videos")
public class CategoriaVideoController {
 
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoModelAssembler videoAssembler;

    @GetMapping
    public Page<VideoModel> findVideosByCategoriaId(@PathVariable Long id, @PageableDefault(size = 5) Pageable pageable){
        var categoria = categoriaService.findOrFail(id);
        Page<Video> videoPage = videoRepository.findByCategoria(categoria, pageable);
        List<VideoModel> videoModelList = videoAssembler.toListModel(videoPage.getContent());
        return new PageImpl<>(videoModelList, pageable, videoPage.getTotalElements());
    }
}

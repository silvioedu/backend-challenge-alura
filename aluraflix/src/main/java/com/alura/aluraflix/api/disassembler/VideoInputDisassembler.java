package com.alura.aluraflix.api.disassembler;

import com.alura.aluraflix.api.input.VideoInput;
import com.alura.aluraflix.domain.model.Categoria;
import com.alura.aluraflix.domain.model.Video;

import org.springframework.stereotype.Component;

@Component
public class VideoInputDisassembler {
 
    public Video toDomain(VideoInput videoInput) {
        var video = new Video();
        video.setTitulo(videoInput.getTitulo());
        video.setDescricao(videoInput.getDescricao());
        video.setUrl(videoInput.getUrl());
        
        var categoria = new Categoria();
        categoria.setId(videoInput.getCategoriaId());
        video.setCategoria(categoria);

        return video;

    }
    
}

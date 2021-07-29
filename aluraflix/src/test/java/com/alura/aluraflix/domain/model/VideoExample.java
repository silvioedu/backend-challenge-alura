package com.alura.aluraflix.domain.model;

import com.alura.aluraflix.api.input.VideoInput;

import org.apache.commons.lang3.RandomUtils;

public abstract class VideoExample {

    public static Video getInstance() {
        Categoria categoria = CategoriaExample.getInstance();

        Video video = new Video();
        video.setId(RandomUtils.nextLong(1, 99));
        video.setTitulo(String.format("Título %d", video.getId()));
        video.setDescricao(String.format("Descrição do vídeo %d", video.getId()));
        video.setUrl(String.format("http://video%d.com", video.getId()));
        video.setCategoria(categoria);
        return video;
    }
    
    public static VideoInput getInputInstance() {
        Categoria categoria = CategoriaExample.getInstance();

        VideoInput videoInput = new VideoInput();
        Long id = RandomUtils.nextLong(1, 99);
        videoInput.setTitulo(String.format("Título %d", id));
        videoInput.setDescricao(String.format("Descrição do vídeo %d", id));
        videoInput.setUrl(String.format("http://video%d.com", id));
        videoInput.setCategoriaId(categoria.getId());
        return videoInput;
    }

}

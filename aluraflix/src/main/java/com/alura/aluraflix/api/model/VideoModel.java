package com.alura.aluraflix.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoModel {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;

}

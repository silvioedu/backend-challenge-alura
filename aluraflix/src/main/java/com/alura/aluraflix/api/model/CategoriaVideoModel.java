package com.alura.aluraflix.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaVideoModel {

    private String titulo;
    private Long videos;
}

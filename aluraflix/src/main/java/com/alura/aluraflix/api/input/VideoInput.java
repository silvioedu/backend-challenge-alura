package com.alura.aluraflix.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoInput {
    
    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotBlank
    private String url;

    @NotNull
    private Long categoriaId;


}

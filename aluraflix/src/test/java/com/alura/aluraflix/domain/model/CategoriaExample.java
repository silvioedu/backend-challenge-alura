package com.alura.aluraflix.domain.model;

import com.alura.aluraflix.api.input.CategoriaInput;

import org.apache.commons.lang3.RandomUtils;

public abstract class CategoriaExample {

    public static Categoria getInstance() {
        Categoria categoria = new Categoria();
        categoria.setId(RandomUtils.nextLong(1, 5));
        categoria.setTitulo(String.format("Título %d", categoria.getId()));
        categoria.setCor(String.valueOf(RandomUtils.nextLong(1, 200)));
        return categoria;
    }
    
    public static CategoriaInput getInputInstance() {
        CategoriaInput categoriaInput = new CategoriaInput();
        Long id = RandomUtils.nextLong(1, 5);
        categoriaInput.setTitulo(String.format("Título %d", id));
        categoriaInput.setCor(String.valueOf(RandomUtils.nextLong(1, 200)));
        return categoriaInput;
    }

}

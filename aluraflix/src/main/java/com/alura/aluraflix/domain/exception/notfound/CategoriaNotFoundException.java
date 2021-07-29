package com.alura.aluraflix.domain.exception.notfound;

public class CategoriaNotFoundException extends EntityNotFoundException {
 
    private static final String NOT_FOUND_MSG = "Categoria %d não encontrada.";

    public CategoriaNotFoundException(Long id) {
        super(String.format(NOT_FOUND_MSG, id));
    }

}

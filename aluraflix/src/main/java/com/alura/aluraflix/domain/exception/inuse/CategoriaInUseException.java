package com.alura.aluraflix.domain.exception.inuse;

public class CategoriaInUseException extends EntityInUseException {
 
    private static final String NOT_FOUND_MSG = "Categoria %d em uso.";

    public CategoriaInUseException(Long id) {
        super(String.format(NOT_FOUND_MSG, id));
    }

}

package com.alura.aluraflix.domain.exception.notfound;

public class EntityNotFoundException extends RuntimeException {
 
    public EntityNotFoundException(String message) {
        super(message);
    }

}

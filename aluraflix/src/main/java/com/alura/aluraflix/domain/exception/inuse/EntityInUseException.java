package com.alura.aluraflix.domain.exception.inuse;

public class EntityInUseException extends RuntimeException {
 
    public EntityInUseException(String message) {
        super(message);
    }

}

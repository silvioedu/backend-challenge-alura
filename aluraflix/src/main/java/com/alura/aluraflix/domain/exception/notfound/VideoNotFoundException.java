package com.alura.aluraflix.domain.exception.notfound;

public class VideoNotFoundException extends EntityNotFoundException {
 
    private static final String NOT_FOUND_MSG = "Vídeo %d não encontrado.";

    public VideoNotFoundException(Long id) {
        super(String.format(NOT_FOUND_MSG, id));
    }

}

package com.alura.aluraflix.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemTypeEnum {
    
    NOT_FOUND("Registro não encontrado");

    private String title;

    private ProblemTypeEnum(String title) {
        this.title = title;
    }
}

package com.alura.aluraflix.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemTypeEnum {
    

    IN_USE("Registro em uso"),
    INVALID_DATA("Dados inválidos"),
    NOT_FOUND("Registro não encontrado")
    ;

    private String title;

    private ProblemTypeEnum(String title) {
        this.title = title;
    }
}

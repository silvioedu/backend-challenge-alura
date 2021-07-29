package com.alura.aluraflix.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Categoria {
 
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String cor;

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", cor='" + getCor() + "'" +
            "}";
    }

}

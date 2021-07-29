package com.alura.aluraflix.api.disassembler;

import com.alura.aluraflix.api.input.CategoriaInput;
import com.alura.aluraflix.domain.model.Categoria;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaInputDisassembler {
 
    @Autowired
    private ModelMapper modelMapper;
    
    public Categoria toDomain(CategoriaInput categoriaInput) {
        return modelMapper.map(categoriaInput, Categoria.class);
    }
    
}

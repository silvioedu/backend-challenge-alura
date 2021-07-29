package com.alura.aluraflix.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.alura.aluraflix.api.model.CategoriaModel;
import com.alura.aluraflix.domain.model.Categoria;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaModelAssembler {
 
    @Autowired
    private ModelMapper modelMapper;
    
    public CategoriaModel toModel(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaModel.class);
    }

    public List<CategoriaModel> toListModel(List<Categoria> categoriaList){
        return categoriaList.stream()
            .map(categoria -> toModel(categoria))
            .collect(Collectors.toList());
    } 
    
}

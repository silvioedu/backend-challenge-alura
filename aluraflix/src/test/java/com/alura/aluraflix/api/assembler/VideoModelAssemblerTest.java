package com.alura.aluraflix.api.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.alura.aluraflix.api.model.CategoriaModel;
import com.alura.aluraflix.domain.model.Categoria;
import com.alura.aluraflix.domain.model.CategoriaExample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoriaModelAssemblerTest {
    
    @Autowired
    private CategoriaModelAssembler assembler;

    @Test
    void shouldConvertCategoria_inCategoriaModel() {

        Categoria categoria = CategoriaExample.getInstance();
        CategoriaModel categoriaModel = assembler.toModel(categoria);
        assertValues(categoria, categoriaModel);
        
    }

    @Test
    void shouldConvertCategoriaList_inCategoriaModelList() {

        int max = 10;
        List<Categoria> categoriaList = new ArrayList<>();
        
        for(int i = 0; i < max; i++) {
            Categoria categoria = CategoriaExample.getInstance();
            categoriaList.add(categoria);            
        }

        List<CategoriaModel> categoriaModelList = assembler.toListModel(categoriaList);

        for(int i = 0; i < max; i++) {
            assertValues(categoriaList.get(i), categoriaModelList.get(i));
        }

    }

    private void assertValues(Categoria categoria, CategoriaModel categoriaModel) {
        assertEquals(categoria.getId(), categoriaModel.getId());
        assertEquals(categoria.getTitulo(), categoriaModel.getTitulo());
        assertEquals(categoria.getCor(), categoriaModel.getCor());
    }

}

package com.alura.aluraflix.api.disassembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alura.aluraflix.api.input.CategoriaInput;
import com.alura.aluraflix.domain.model.Categoria;
import com.alura.aluraflix.domain.model.CategoriaExample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoriaInputDisassemblerTest {

    @Autowired
    private CategoriaInputDisassembler disassembler;
    
    @Test
    void shouldConvertCategoriaInput_inCategoria() {
        CategoriaInput categoriaInput = CategoriaExample.getInputInstance();
        Categoria categoria = disassembler.toDomain(categoriaInput);
        assertValues(categoriaInput, categoria);
    }

    private void assertValues(CategoriaInput categoriaInput, Categoria categoria) {
        assertEquals(categoriaInput.getTitulo(), categoria.getTitulo());
        assertEquals(categoriaInput.getCor(), categoria.getCor());
    }

}

package com.alura.aluraflix.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.alura.aluraflix.api.assembler.CategoriaModelAssembler;
import com.alura.aluraflix.api.disassembler.CategoriaInputDisassembler;
import com.alura.aluraflix.api.input.CategoriaInput;
import com.alura.aluraflix.api.model.CategoriaModel;
import com.alura.aluraflix.domain.repository.CategoriaRepository;
import com.alura.aluraflix.domain.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
 
    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaModelAssembler assembler;

    @Autowired
    private CategoriaInputDisassembler disassembler;

    @GetMapping
    public List<CategoriaModel> findAll() {
        return assembler.toListModel(categoriaRepository.findAll()); 
    }

    @GetMapping("/{id}")
    public CategoriaModel findById(@PathVariable Long id) {
        return assembler.toModel(service.findOrFail(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaModel insert(@RequestBody @Valid CategoriaInput categoriaInput) {
        var categoria = disassembler.toDomain(categoriaInput);
        return assembler.toModel(service.save(categoria));
    }

    @PutMapping("/{id}")
    public CategoriaModel update(@RequestBody @Valid CategoriaInput categoriaInput, @PathVariable Long id) {
        var categoriaSaved = service.findOrFail(id);
        var categoria = disassembler.toDomain(categoriaInput);
        categoria.setId(categoriaSaved.getId());
        return assembler.toModel(service.save(categoria));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

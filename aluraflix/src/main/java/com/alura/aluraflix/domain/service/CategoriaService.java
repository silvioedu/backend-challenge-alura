package com.alura.aluraflix.domain.service;

import com.alura.aluraflix.domain.exception.inuse.CategoriaInUseException;
import com.alura.aluraflix.domain.exception.notfound.CategoriaNotFoundException;
import com.alura.aluraflix.domain.model.Categoria;
import com.alura.aluraflix.domain.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repository;
 
    public Categoria findOrFail(Long id){
        return repository.findById(id)
            .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public void delete(Long id) {
        var categoria = this.findOrFail(id);
        
        try {
            repository.delete(categoria);
        } catch (DataIntegrityViolationException e) {
            throw new CategoriaInUseException(id);
        }
        
    }

}

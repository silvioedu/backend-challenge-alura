package com.alura.aluraflix.api.controller;

import java.util.List;

import com.alura.aluraflix.api.model.CategoriaVideoModel;
import com.alura.aluraflix.domain.service.RelatorioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
    
    @Autowired
    private RelatorioService service;

    @GetMapping("/categorias-videos")
    public List<CategoriaVideoModel> reportByCategoria() {
        return service.findAllVideosByCategoria();
    }
}

package com.alura.aluraflix.api.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.alura.aluraflix.api.assembler.VideoModelAssembler;
import com.alura.aluraflix.api.disassembler.VideoInputDisassembler;
import com.alura.aluraflix.api.input.VideoInput;
import com.alura.aluraflix.api.model.VideoModel;
import com.alura.aluraflix.domain.model.Video;
import com.alura.aluraflix.domain.repository.VideoRepository;
import com.alura.aluraflix.domain.service.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
public class VideoController {
 
    @Autowired
    private VideoService service;

    @Autowired
    private VideoRepository repository;

    @Autowired
    private VideoModelAssembler assembler;

    @Autowired
    private VideoInputDisassembler disassembler;

    @GetMapping
    public Page<VideoModel> findAll(@RequestParam(required = false) String search, @PageableDefault(size = 5) Pageable pageable) {
        Page<Video> videoPage = Objects.isNull(search) ? 
            repository.findAll(pageable) : 
            repository.findByTituloContains(search, pageable);
        List<VideoModel> videoModelList = assembler.toListModel(videoPage.getContent());
        return new PageImpl<>(videoModelList, pageable, videoPage.getTotalPages());
    }

    @GetMapping("/{id}")
    public VideoModel findById(@PathVariable Long id) {
        return assembler.toModel(service.findOrFail(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoModel insert(@RequestBody @Valid VideoInput videoInput) {
        var video = disassembler.toDomain(videoInput);
        return assembler.toModel(service.save(video));
    }

    @PutMapping("/{id}")
    public VideoModel update(@RequestBody @Valid VideoInput videoInput, @PathVariable Long id) {
        var videoSaved = service.findOrFail(id);
        var video = disassembler.toDomain(videoInput);
        video.setId(videoSaved.getId());
        return assembler.toModel(service.save(video));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
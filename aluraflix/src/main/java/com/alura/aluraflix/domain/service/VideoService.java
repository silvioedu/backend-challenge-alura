package com.alura.aluraflix.domain.service;

import com.alura.aluraflix.domain.exception.VideoNotFoundException;
import com.alura.aluraflix.domain.model.Video;
import com.alura.aluraflix.domain.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    
    @Autowired
    private VideoRepository repository;
 
    public Video findOrFail(Long id){
        return repository.findById(id)
            .orElseThrow(() -> new VideoNotFoundException(id));
    }

    public Video save(Video video) {
        return repository.save(video);
    }

    public void delete(Long id) {
        var video = this.findOrFail(id);
        repository.delete(video);
    }

}

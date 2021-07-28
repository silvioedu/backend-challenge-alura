package com.alura.aluraflix.api.disassembler;

import com.alura.aluraflix.api.input.VideoInput;
import com.alura.aluraflix.domain.model.Video;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoInputDisassembler {
 
    @Autowired
    private ModelMapper modelMapper;
    
    public Video toDomain(VideoInput videoInput) {
        return modelMapper.map(videoInput, Video.class);
    }
    
}

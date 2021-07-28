package com.alura.aluraflix.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.alura.aluraflix.api.model.VideoModel;
import com.alura.aluraflix.domain.model.Video;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoModelAssembler {
 
    @Autowired
    private ModelMapper modelMapper;
    
    public VideoModel toModel(Video video) {
        return modelMapper.map(video, VideoModel.class);
    }

    public List<VideoModel> toListModel(List<Video> videoList){
        return videoList.stream()
            .map(video -> toModel(video))
            .collect(Collectors.toList());
    } 
    
}

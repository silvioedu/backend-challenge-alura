package com.alura.aluraflix.api.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.alura.aluraflix.api.model.VideoModel;
import com.alura.aluraflix.domain.model.Video;
import com.alura.aluraflix.domain.model.VideoExample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoModelAssemblerTest {
    
    @Autowired
    private VideoModelAssembler assembler;

    @Test
    void shouldConvertVideo_inVideoModel() {

        Video video = VideoExample.getInstance();
        VideoModel videoModel = assembler.toModel(video);
        assertValues(video, videoModel);
        
    }

    void shouldConvertVideoList_inVideoModelList() {

        int max = 10;
        List<Video> videoList = new ArrayList<>();
        
        for(int i = 0; i < max; i++) {
            Video video = VideoExample.getInstance();
            videoList.add(video);            
        }

        List<VideoModel> videoModelList = assembler.toListModel(videoList);

        for(int i = 0; i < max; i++) {
            assertValues(videoList.get(i), videoModelList.get(i));
        }

    }

    private void assertValues(Video video, VideoModel videoModel) {
        assertEquals(video.getId(), videoModel.getId());
        assertEquals(video.getTitulo(), videoModel.getTitulo());
        assertEquals(video.getDescricao(), videoModel.getDescricao());
        assertEquals(video.getUrl(), videoModel.getUrl());
    }

}

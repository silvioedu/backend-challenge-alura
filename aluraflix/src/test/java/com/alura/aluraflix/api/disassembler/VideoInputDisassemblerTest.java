package com.alura.aluraflix.api.disassembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alura.aluraflix.api.input.VideoInput;
import com.alura.aluraflix.domain.model.Video;
import com.alura.aluraflix.domain.model.VideoExample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoInputDisassemblerTest {

    @Autowired
    private VideoInputDisassembler disassembler;
    
    @Test
    void shouldConvertVideoInput_inVideo() {
        VideoInput videoInput = VideoExample.getInputInstance();
        Video video = disassembler.toDomain(videoInput);
        assertValues(videoInput, video);
    }

    private void assertValues(VideoInput videoInput, Video video) {
        assertEquals(videoInput.getTitulo(), video.getTitulo());
        assertEquals(videoInput.getDescricao(), video.getDescricao());
        assertEquals(videoInput.getUrl(), video.getUrl());
    }

}

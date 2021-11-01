package com.back.websters.service;

import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.video.Video;
import com.back.websters.domain.video.VideoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest()
class DataServiceTest {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private DataService dataService;

    @AfterEach
    void afterEach() {
        videoRepository.deleteAll();
    }

    @Test
    void Video_저장() {
        // given
        String videoName = "myvideo.mp4";
        String videoUri = "S3://test/myvideo.mp4";
        long id = dataService.saveVideo(videoName, videoUri);

        // then
        Video result = videoRepository.findById(id).get();

        // when
        assertThat(result.getName()).isEqualTo(videoName);
        assertThat(result.getUri()).isEqualTo(videoUri);

    }

    @Test
    void Video_이름으로_조회() {
        // given
        String videoName = "myvideo.mp4";
        String videoUri = "S3://test/myvideo.mp4";
        long id = dataService.saveVideo(videoName, videoUri);

        // then
        Video result = dataService.findVideoByName(videoName);

        // when
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUri()).isEqualTo(videoUri);

    }

}
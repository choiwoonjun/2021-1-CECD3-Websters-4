package com.back.websters.service;

import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.bookmark.BookmarkRepository;
import com.back.websters.domain.script.Script;
import com.back.websters.domain.script.ScriptRepository;
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
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private ScriptRepository scriptRepository;
    @Autowired
    private DataService dataService;

    @AfterEach
    void afterEach() {
        bookmarkRepository.deleteAll();
        scriptRepository.deleteAll();
        videoRepository.deleteAll();
    }

    @Test
    void Video_저장() {
        // given
        String videoName = "myvideo.mp4";
        String videoUri = "S3://test/myvideo.mp4";
        long id = dataService.saveVideo(videoName, videoUri);

        // when
        Video result = videoRepository.findById(id).get();

        // then
        assertThat(result.getName()).isEqualTo(videoName);
        assertThat(result.getUri()).isEqualTo(videoUri);

    }

    @Test
    void Video_이름으로_조회() {
        // given
        String videoName = "myvideo.mp4";
        String videoUri = "S3://test/myvideo.mp4";
        long id = dataService.saveVideo(videoName, videoUri);

        // when
        Video result = dataService.findVideoByName(videoName);

        // then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUri()).isEqualTo(videoUri);

    }

    @Test
    void 북마크_저장() {
        // given
        String videoName = "myvideo.mp4";
        String videoUri = "S3://test/myvideo.mp4";
        long videoId = dataService.saveVideo(videoName, videoUri);

        String time = "00:55";
        long bookmarkId = dataService.saveBookmark(videoRepository.findById(videoId).get().getId(), time);

        // when
        Bookmark result = bookmarkRepository.findById(bookmarkId).get();

        // then
        assertThat(result.getId()).isEqualTo(bookmarkId);
        assertThat(result.getTime()).isEqualTo(time);

    }

    @Test
    void 스크립트_저장() {
        // given
        String videoName = "myvideo.mp4";
        String videoUri = "S3://test/myvideo.mp4";
        long videoId = dataService.saveVideo(videoName, videoUri);

        String startTime = "00:00";
        String endTime = "00:03";
        String content = "테스트";
        Script script = Script.builder()
                .startTime(startTime)
                .endTime(endTime)
                .content(content).build();
        long scriptId = dataService.saveScript(videoId, script);

        // when
        Script result = scriptRepository.findById(scriptId).get();


        // then
        assertThat(result.getContent()).isEqualTo(content);
        assertThat(result.getStartTime()).isEqualTo(startTime);
        assertThat(result.getEndTime()).isEqualTo(endTime);

    }

}
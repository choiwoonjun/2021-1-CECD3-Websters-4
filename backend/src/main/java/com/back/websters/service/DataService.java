package com.back.websters.service;

import com.back.websters.domain.video.Video;
import com.back.websters.domain.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DataService {

    private final VideoRepository videoRepository;

    public long saveVideo(String fileName, String fileUri) {
        Video video = Video.builder()
                .name(fileName)
                .uri(fileUri)
                .build();
        return videoRepository.save(video).getId();
    }

    public Video findVideoByName(String fileName) {
        return videoRepository.findByName(fileName)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다."));
    }
}

package com.back.websters.service;

import com.back.websters.domain.video.Video;
import com.back.websters.domain.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public String getVideoList() {
        List<Video> result = videoRepository.findAll();
        JSONArray videoList = new JSONArray();
        for (Video video : result) {
            videoList.put(new JSONObject()
                    .put("video_id", video.getId())
                    .put("video_name", video.getName())
                    .put("video_uri", video.getUri()));
        }
        return new JSONObject().put("result", videoList).toString();
    }

    public long saveVideo(String fileName, String fileUri) {
        Video video = Video.builder()
                .name(fileName)
                .uri(fileUri)
                .build();
        return videoRepository.save(video).getId();
    }

    public Video findVideoById(long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다."));
    }

    public Video findVideoByName(String videoName) {
        return videoRepository.findByName(videoName)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다."));
    }

}

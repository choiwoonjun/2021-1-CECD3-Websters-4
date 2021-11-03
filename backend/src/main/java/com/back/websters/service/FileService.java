package com.back.websters.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.back.websters.domain.script.Script;
import com.back.websters.domain.video.Video;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {

    private final S3Service s3Service;
    private final DataService dataService;

    public String transcribe(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            return s3Service.uploadAndTranscribe(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 변환 중 오류가 발생했습니다." + file.getOriginalFilename());
        }
    }

    public String getVideoList() {
        List<Video> videoList = dataService.findAllVideos();
        JSONArray array = new JSONArray();
        for (Video video : videoList) {
            array.put(new JSONObject()
                    .put("id", video.getId())
                    .put("name", video.getName())
                    .put("uri", video.getUri()));
        }
        JSONObject result = new JSONObject().put("result", array);
        return result.toString();
    }

    public String getScripts(String videoName) {
        List<Script> result = dataService.findScripts(videoName);
        JSONArray scripts = new JSONArray();
        for (Script script : result) {
            scripts.put(new JSONObject()
                    .put("start_time", script.getStartTime())
                    .put("end_time", script.getEndTime())
                    .put("content", script.getContent()));
        }
        return new JSONObject().put("results", scripts).toString();
    }

}

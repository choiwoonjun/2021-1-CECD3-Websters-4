package com.back.websters.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.back.websters.component.FilePathComponent;
import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.script.Script;
import com.back.websters.domain.video.Video;
import com.back.websters.utils.FileUtils;
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
    private final FilePathComponent filePathComponent;
    private final FileUtils fileNameUtils;

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
        List<Video> result = dataService.findAllVideos();
        JSONArray videoList = new JSONArray();
        for (Video video : result) {
            videoList.put(new JSONObject()
                    .put("video_id", video.getId())
                    .put("video_name", video.getName())
                    .put("video_uri", video.getUri()));
        }
        return new JSONObject().put("result", videoList).toString();
    }

    public String getScripts(String videoName) {
        List<Script> result = dataService.findScripts(videoName);
        JSONArray scripts = new JSONArray();
        for (Script script : result) {
            scripts.put(new JSONObject()
                    .put("script_id", script.getId())
                            .put("video_id", script.getVideo().getId())
                    .put("start_time", script.getStartTime())
                    .put("end_time", script.getEndTime())
                    .put("content", script.getContent()));
        }
        return new JSONObject().put("results", scripts).toString();
    }

    public String getBookmarks(String videoName) {
        List<Bookmark> result = dataService.findBookmarks(videoName);
        JSONArray bookmarks = new JSONArray();
        for (Bookmark bookmark : result) {
            bookmarks.put(new JSONObject()
                    .put("bookmark_id", bookmark.getId())
                    .put("video_id", bookmark.getVideo().getId())
                    .put("time", bookmark.getTime()));
        }
        return new JSONObject().put("results", bookmarks).toString();
    }
}

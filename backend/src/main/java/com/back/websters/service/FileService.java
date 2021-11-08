package com.back.websters.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.back.websters.component.FilePathComponent;
import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.script.Script;
import com.back.websters.domain.video.Video;
import com.back.websters.utils.FileNameUtils;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {

    private final S3Service s3Service;
    private final DataService dataService;
    private final FilePathComponent filePathComponent;
    private final FileNameUtils fileNameUtils;

    public String transcribe(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // application.yml 에서 파일 경로 가져옴
        String localPath = filePathComponent.getLocation();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        // 디렉토리 있는지 확인
        if (!new File(localPath).exists()) {
            try{
                new File(localPath).mkdir();
            }
            catch(Exception e) {
                e.getStackTrace();
            }
        }

        try (InputStream inputStream = file.getInputStream()) {
            // 북마크 기능 사용을 위해 로컬에 파일 저장
            File localFile = new File(localPath + '/' + fileNameUtils.createRandomName() + '.' + fileNameUtils.getFileExtension(fileName));
            file.transferTo(localFile);

            /*
             여기에 북마크 생성 코드 삽입
             */

            localFile.delete();
            return s3Service.uploadAndTranscribe(inputStream, objectMetadata, fileName);
            //return "";
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("파일 변환 중 오류가 발생했습니다. " + file.getOriginalFilename());
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

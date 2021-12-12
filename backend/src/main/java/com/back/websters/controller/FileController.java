package com.back.websters.controller;

import com.back.websters.component.utils.BookmarkGenerator;
import com.back.websters.service.BookmarkService;
import com.back.websters.service.ScriptService;
import com.back.websters.service.VideoService;
import com.back.websters.component.utils.S3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final VideoService videoService;
    private final BookmarkService bookmarkService;
    private final ScriptService scriptService;
    private final S3Utils s3Utils;
    private final BookmarkGenerator bookmarkGenerator;

    @PostMapping("/api/v1/video")
    public String saveVideo(@RequestPart MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String s3Uri = s3Utils.uploadToS3(file); // 비디오 s3 저장
        long videoId = videoService.saveVideo(fileName, s3Uri); // video db 저장
        String jobName = s3Utils.transcribe(file.getOriginalFilename()); // AWS Transcribe
        bookmarkService.saveBookmarks(bookmarkGenerator.generateBookmarks(file), videoId);
        scriptService.createScripts(s3Utils.getTranscriptionResult(jobName), videoId);
        return "";
    }

    @GetMapping("/api/v1/videos")
    public String getVideoList() {
        return videoService.getVideoList();
    }

    @GetMapping("/api/v1/scripts/{videoName}")
    public String getScripts(@PathVariable String videoName) {
        return scriptService.findScripts(videoName);
    }

    @GetMapping("/api/v1/bookmarks/{videoName}")
    public String getBookmarks(@PathVariable String videoName) {
        return bookmarkService.findBookmarks(videoName);
    }

}

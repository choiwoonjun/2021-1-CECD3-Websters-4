package com.back.websters.controller;

import com.back.websters.domain.video.Video;
import com.back.websters.service.BookmarkService;
import com.back.websters.service.DataService;
import com.back.websters.service.FileService;
import com.back.websters.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;
    private final S3Service s3Service;
    private final BookmarkService bookmarkService;

    @PostMapping("/api/v1/video")
    public String saveVideo(@RequestPart MultipartFile file) {
        long videoId = fileService.uploadToS3(file); // 비디오 s3, db 저장
        String localFilePath = fileService.saveToLocal(file);
        bookmarkService.save(localFilePath, videoId);// 비디오 북마크 생성, db 저장
        s3Service.transcribe(file.getOriginalFilename(), videoId); // AWS Transcribe
        fileService.deleteByLocal(localFilePath);
        return Long.toString(videoId);
    }

    @GetMapping("/api/v1/videos")
    public String getVideoList() {
        return fileService.getVideoList();
    }

    @GetMapping("/api/v1/scripts/{videoName}")
    public String getScripts(@PathVariable String videoName) {
        return fileService.getScripts(videoName);
    }

    @GetMapping("/api/v1/bookmarks/{videoName}")
    public String getBookmarks(@PathVariable String videoName) {
        return fileService.getBookmarks(videoName);
    }

}

package com.back.websters.controller;

import com.back.websters.domain.video.Video;
import com.back.websters.service.BookmarkService;
import com.back.websters.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;
    private final BookmarkService bookmarkService;

//    @PostMapping("/api/v1/transcribe")
//    public String transcribe(@RequestPart MultipartFile file) {
//        Video video = null;
//        bookmarkService.save(file,video);
//        return "";
////        return fileService.transcribe(file);
//    }

    @PostMapping("/api/v1/video")// video
    public String saveVideo(@RequestPart MultipartFile file) {
        // Video video =  VideoService.save(file) // 비디오 s3, local 저장
        Video video = null;
        bookmarkService.save(file,video);// 북마크 생성, db 저장
        fileService.transcribe(file); // script 생성, db 저장
        return "";
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

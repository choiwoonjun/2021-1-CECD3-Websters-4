package com.back.websters.controller;

import com.back.websters.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("/api/v1/transcribe")
    public String transcribe(@RequestPart MultipartFile file) {
        return fileService.transcribe(file);
    }

    @GetMapping("/api/v1/videos")
    public String getVideoList() {
        return fileService.getVideoList();
    }

    @GetMapping("/api/v1/scripts/{videoName}")
    public String getScripts(@PathVariable String videoName) {
        return fileService.getScripts(videoName);
    }

}

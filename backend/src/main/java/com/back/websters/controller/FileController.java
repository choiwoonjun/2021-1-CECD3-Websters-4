package com.back.websters.controller;

import com.back.websters.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("/api/v1/upload")
    public String uploadFile(@RequestPart MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @PostMapping("/api/v1/transcribe")
    public String transcribeFile(@RequestParam("url") String fileUrl) {
        return fileService.transcribe(fileUrl);
    }
}

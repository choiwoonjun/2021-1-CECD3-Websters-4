package com.back.websters.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FileNameUtils {

    public String createRandomName() {
        return UUID.randomUUID().toString();
    }

    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("잘못된 형식의 파일입니다.");
        }
    }
}

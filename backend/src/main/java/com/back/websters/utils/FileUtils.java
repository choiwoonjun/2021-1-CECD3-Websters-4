package com.back.websters.utils;

import com.back.websters.component.FilePathComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FileUtils {
    private final FilePathComponent filePathComponent;

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

    public String saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String localPath = filePathComponent.getLocation();
        String filePath = "";
        try {
            // 디렉토리 있는지 확인
            if (!new File(localPath).exists()) {
                new File(localPath).mkdir();
            }

            // 북마크 기능 사용을 위해 로컬에 파일 저장
            filePath = localPath + '/' + createRandomName() + '.' + getFileExtension(fileName);
            File localFile = new File(filePath);
            file.transferTo(localFile);

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("파일 저장 중 오류가 발생했습니다. " + file.getOriginalFilename());
        }
        return filePath;
    }

    public void deleteFile(String filePath) {
        File localFile = new File(filePath);
        boolean deleteSuccess = localFile.delete();

        if(!deleteSuccess) {
            throw new IllegalArgumentException("파일 삭제 중 오류가 발생했습니다. " + filePath);
        }
    }

}

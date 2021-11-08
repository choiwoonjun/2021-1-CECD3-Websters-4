package com.back.websters.service;

import com.back.websters.component.FilePathComponent;
import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.bookmark.BookmarkRepository;
import com.back.websters.domain.video.VideoRepository;
import com.back.websters.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final FileUtils fileNameUtils;

    public String save(MultipartFile file) {
        String filePath = fileNameUtils.saveFile(file);


        fileNameUtils.deleteFile(filePath);
        return "";
    }

    public String create(String filePath) {
        try {
            String generaterPath = "";
            ProcessBuilder processBuilder = new ProcessBuilder("python", generaterPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            String pythonResult = getPythonStream(process.getInputStream());

            int exitCode = process.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    static String getPythonStream(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

}

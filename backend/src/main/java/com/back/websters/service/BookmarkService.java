package com.back.websters.service;

import com.back.websters.component.BoookmarkGeneratorPathComponent;
import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.bookmark.BookmarkRepository;
import com.back.websters.domain.video.Video;
import com.back.websters.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BoookmarkGeneratorPathComponent Generator;
    private final DataService dataService;

    public void save(String filePath, long videoId) {
        List<Bookmark> bookmarks = getBookmarks(filePath, videoId);
        System.out.println(bookmarks);
        dataService.saveBookmarks(bookmarks);
    }

    public List<Bookmark> getBookmarks(String filePath, long videoId) {
        String times = getTimes(filePath);
        String[] split = times.split(" ");
        Video video = dataService.findVideoById(videoId);
        return Arrays.stream(split)
                .map(o -> Bookmark.builder().time(o).video(video).build()).collect(Collectors.toList());
    }

    public String getTimes(String filePath) {
        String result="";
        try {
            String generatorPath = Generator.getPath();
            ProcessBuilder processBuilder = new ProcessBuilder("python", generatorPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            OutputStream stdin = process.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stdin));
            bw.write(filePath);
            bw.flush();
            bw.close();

            result = getPythonStream(process.getInputStream());

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IllegalArgumentException("북마크 생성 도중 오류가 발생했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        return result;
    }

    static String getPythonStream(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

}

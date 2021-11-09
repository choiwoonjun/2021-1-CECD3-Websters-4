package com.back.websters.component.utils;

import com.back.websters.component.property.BookmarkProperty;
import com.back.websters.domain.bookmark.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookmarkGenerator {

    private final BookmarkProperty property;
    private final FileUtils fileUtils;

    public List<Bookmark> generateBookmarks(MultipartFile file) {
        String filePath = fileUtils.saveToLocal(file);
        String times = getTimes(filePath);
        String[] split = times.split(" ");
        return Arrays.stream(split)
                .map(o -> Bookmark.builder().time(o).build()).collect(Collectors.toList());
    }

    private String getTimes(String filePath) {
        try {
            String generatorPath = property.getPath();
            ProcessBuilder processBuilder = new ProcessBuilder("python", generatorPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            OutputStream stdin = process.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stdin));
            bw.write(filePath);
            bw.flush();
            bw.close();

            String result = getPythonStream(process.getInputStream());

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IllegalArgumentException("북마크 생성 도중 오류가 발생했습니다.");
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("북마크 생성 도중 오류가 발생했습니다.");
        }
    }

    public String getPythonStream(InputStream inputStream) throws IOException {
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

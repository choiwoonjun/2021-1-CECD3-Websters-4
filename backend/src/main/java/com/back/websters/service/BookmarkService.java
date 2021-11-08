package com.back.websters.service;

import com.back.websters.component.BoookmarkGeneratorPathComponent;
import com.back.websters.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final FileUtils fileUtils;
    private final BoookmarkGeneratorPathComponent Generator;

    public String save(MultipartFile file) {
        String filePath = fileUtils.saveFile(file);
        String bookmarks = getBookmarks(filePath);
        fileUtils.deleteFile(filePath);
        return bookmarks;
    }

    public String getBookmarks(String filePath) {
        String result="";
        try {
            String generaterPath = Generator.getPath();

            ProcessBuilder processBuilder = new ProcessBuilder("python", generaterPath);
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
                throw new Exception("북마크 생성 도중 오류");
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

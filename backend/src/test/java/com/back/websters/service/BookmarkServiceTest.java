package com.back.websters.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookmarkServiceTest {

    static String getPythonResult(InputStream inputStream) throws IOException {
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


    @Test
    public void 파이썬_실행_테스트() throws Exception {
        String path = "C:\\Users\\김건오\\Desktop\\2021-1-CECD3-Websters-4\\backend\\src\\test\\resources\\test.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python", path);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        String s = getPythonResult(process.getInputStream());
        int exitCode = process.waitFor();
        System.out.println("s = " + s);
        System.out.println("exitCode = " + exitCode);

    }

    @Test
    public void 북마크_생성_테스트() throws Exception {

        String path = "C:\\Users\\김건오\\Desktop\\2021-1-CECD3-Websters-4\\bookmark\\pythonTest.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python", path);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        String pythonResult = getPythonResult(process.getInputStream());

        int exitCode = process.waitFor();
        System.out.println("pythonResult = " + pythonResult);

        assertThat(exitCode).isEqualTo(0);

    }
}
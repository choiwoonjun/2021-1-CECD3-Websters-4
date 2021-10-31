package com.back.websters.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.back.websters.component.CredentialsComponent;
import com.back.websters.component.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.transcribe.TranscribeClient;
import software.amazon.awssdk.services.transcribe.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final S3Component s3Component;
    private final CredentialsComponent credentialsComponent;

    public String uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(s3Component.getBucket(), fileName, inputStream, objectMetadata);
        amazonS3Client.putObject(putObjectRequest);
        return transcribe(fileName);
    }

    private String getS3Uri(String fileName) {
        return "s3://" + s3Component.getBucket() + "/" + fileName;
    }

    public String transcribe(String fileName) {
        // 확장자를 체크하여 지원하는 파일 형식인지 확인
        MediaFormat fileExtension;
        try {
            fileExtension = MediaFormat.fromValue(getFileExtension(fileName).toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 형식의 파일입니다.");
        }
        if (fileExtension.equals(MediaFormat.UNKNOWN_TO_SDK_VERSION)) {
            throw new IllegalArgumentException("지원하지 않는 파일 확장자입니다.");
        }

        AwsCredentials credentials = AwsBasicCredentials.create(credentialsComponent.getAccessKey(), credentialsComponent.getSecretKey());
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        TranscribeClient transcribeClient = TranscribeClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_NORTHEAST_2)
                .build();
        String jobName = createJobName();
        StartTranscriptionJobRequest transcriptionJobRequest = StartTranscriptionJobRequest.builder()
                .identifyLanguage(true) // 입력 음성 국적 자동 감지
                .media(Media.builder()
                        .mediaFileUri(getS3Uri(fileName))
                        .build())
                .mediaFormat(fileExtension)
                .outputBucketName(s3Component.getBucket())
                .transcriptionJobName(jobName)
                .build();
        TranscriptionJob result = transcribeClient.startTranscriptionJob(transcriptionJobRequest).transcriptionJob();
        if (!result.transcriptionJobStatus().equals(TranscriptionJobStatus.COMPLETED) || !result.transcriptionJobStatus().equals(TranscriptionJobStatus.FAILED)) {
            GetTranscriptionJobRequest getTranscriptionJobRequest = GetTranscriptionJobRequest.builder()
                    .transcriptionJobName(jobName)
                    .build();
            while (true) {
                result = transcribeClient.getTranscriptionJob(getTranscriptionJobRequest).transcriptionJob();
                if (result.transcriptionJobStatus().equals(TranscriptionJobStatus.COMPLETED)) {
                    try {
                        System.out.println(getTranscriptionResult(jobName));
                        return result.transcriptionJobStatusAsString();
                    } catch (IOException e) {
                        throw new IllegalArgumentException("결과에 오류가 있습니다.");
                    }
                }
                if (result.transcriptionJobStatus().equals(TranscriptionJobStatus.FAILED)) {
                    throw new IllegalArgumentException("Transcribe 과정에서 오류가 발생했습니다.");
                }
            }
        }
        return result.transcriptionJobStatusAsString();
    }

    private String createJobName() {
        return UUID.randomUUID().toString();
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("잘못된 형식의 파일입니다.");
        }
    }

    private String getTranscriptionResult(String jobName) throws IOException {
        S3ObjectInputStream is = amazonS3Client.getObject(s3Component.getBucket(), jobName + ".json").getObjectContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}

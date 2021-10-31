package com.back.websters.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.back.websters.component.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.transcribe.TranscribeClient;
import software.amazon.awssdk.services.transcribe.model.*;

import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final S3Component component;

    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(component.getBucket(), fileName, inputStream, objectMetadata);
        amazonS3Client.putObject(putObjectRequest);
    }

    public String getFileUrl(String fileName) {
        return amazonS3Client.getResourceUrl(component.getBucket(), fileName);
    }

    public String transcribe(String url) {
        AwsCredentialsProvider credentialsProvider = DefaultCredentialsProvider.builder().build();
        TranscribeClient transcribeClient = TranscribeClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_NORTHEAST_2)
                .build();
        StartTranscriptionJobRequest transcriptionJobRequest = StartTranscriptionJobRequest.builder()
                .languageCode(LanguageCode.KO_KR)
                .media(Media.builder()
                        .mediaFileUri(url)
                        .build())
                .mediaFormat(MediaFormat.MP4)
                .outputBucketName(component.getBucket())
                .transcriptionJobName(createJobName())
                .build();
        TranscriptionJob result = transcribeClient.startTranscriptionJob(transcriptionJobRequest).transcriptionJob();
        System.out.println(result);
        return "";
    }

    private String createJobName() {
        return UUID.randomUUID().toString();
    }
}

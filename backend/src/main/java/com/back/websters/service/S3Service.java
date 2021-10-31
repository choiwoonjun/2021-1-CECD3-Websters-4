package com.back.websters.service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.back.websters.component.CredentialsComponent;
import com.back.websters.component.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.transcribe.TranscribeClient;
import software.amazon.awssdk.services.transcribe.model.*;

import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final S3Component s3Component;
    private final CredentialsComponent credentialsComponent;

    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(s3Component.getBucket(), fileName, inputStream, objectMetadata);
        amazonS3Client.putObject(putObjectRequest);
    }

    public String getFileUrl(String fileName) {
        return amazonS3Client.getResourceUrl(s3Component.getBucket(), fileName);
    }

    public String transcribe(String url) {
        AwsCredentials credentials = AwsBasicCredentials.create(credentialsComponent.getAccessKey(), credentialsComponent.getSecretKey());
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
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
                .outputBucketName(s3Component.getBucket())
                .transcriptionJobName(createJobName())
                .build();
        TranscriptionJob result = transcribeClient.startTranscriptionJob(transcriptionJobRequest).transcriptionJob();
        return result.transcriptionJobStatusAsString();
    }

    private String createJobName() {
        return UUID.randomUUID().toString();
    }
}

package com.back.websters.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.back.websters.component.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

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
        return amazonS3Client.getUrl(component.getBucket(), fileName).toString();
    }
}

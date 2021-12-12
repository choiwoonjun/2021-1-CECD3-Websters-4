package com.back.websters.component.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.back.websters.component.property.CredentialsProperty;
import com.back.websters.component.property.S3Property;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.transcribe.TranscribeClient;
import software.amazon.awssdk.services.transcribe.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Component
public class S3Utils {

    private final AmazonS3Client amazonS3Client;
    private final S3Property s3Property;
    private final CredentialsProperty credentialsProperties;
    private final FileUtils fileUtils;

    public String uploadToS3(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try {
            InputStream inputStream = file.getInputStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest(s3Property.getBucket(), fileName, inputStream, objectMetadata);
            // 확장자를 체크하여 지원하는 파일 형식인지 확인
            MediaFormat fileExtension;
            try {
                fileExtension = MediaFormat.fromValue(fileUtils.getFileExtension(fileName).toLowerCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("잘못된 형식의 파일입니다.");
            }
            if (fileExtension.equals(MediaFormat.UNKNOWN_TO_SDK_VERSION)) {
                throw new IllegalArgumentException("지원하지 않는 파일 확장자입니다.");
            }
            amazonS3Client.putObject(putObjectRequest);
            return getS3Uri(fileName);
        } catch (Exception e) {
            throw new IllegalArgumentException("S3 버킷에 저장 중 오류가 발생했습니다.");
        }
    }

    private String getS3Uri(String fileName) {
        return "s3://" + s3Property.getBucket() + "/" + fileName;
    }

    public String transcribe(String fileName) {
        // 확장자를 체크하여 지원하는 파일 형식인지 확인
        MediaFormat fileExtension;
        try {
            fileExtension = MediaFormat.fromValue(fileUtils.getFileExtension(fileName).toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 형식의 파일입니다.");
        }

        AwsCredentials credentials = AwsBasicCredentials.create(credentialsProperties.getAccessKey(), credentialsProperties.getSecretKey());
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        TranscribeClient transcribeClient = TranscribeClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_NORTHEAST_2)
                .build();
        String jobName = fileUtils.createRandomName();
        StartTranscriptionJobRequest transcriptionJobRequest = StartTranscriptionJobRequest.builder()
                .identifyLanguage(true) // 입력 음성 국적 자동 감지
                .media(Media.builder()
                        .mediaFileUri(getS3Uri(fileName))
                        .build())
                .mediaFormat(fileExtension)
                .outputBucketName(s3Property.getBucket())
                .transcriptionJobName(jobName)
                .build();

        transcribeClient.startTranscriptionJob(transcriptionJobRequest);

        return jobName;
    }

    public String getTranscriptionResult(String jobName) {
        AwsCredentials credentials = AwsBasicCredentials.create(credentialsProperties.getAccessKey(), credentialsProperties.getSecretKey());
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        TranscribeClient transcribeClient = TranscribeClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_NORTHEAST_2)
                .build();

        GetTranscriptionJobRequest getTranscriptionJobRequest = GetTranscriptionJobRequest.builder()
                .transcriptionJobName(jobName)
                .build();

        TranscriptionJob result;

        while (true) {
            result = transcribeClient.getTranscriptionJob(getTranscriptionJobRequest).transcriptionJob();
            if (result.transcriptionJobStatus().equals(TranscriptionJobStatus.COMPLETED)) {
                try {
                    S3ObjectInputStream is = amazonS3Client.getObject(s3Property.getBucket(), jobName + ".json").getObjectContent();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    return sb.toString();
                } catch (IOException e) {
                    throw new IllegalArgumentException("결과에 오류가 있습니다.");
                } catch (JSONException e) {
                    throw new IllegalArgumentException("변환 과정에서 오류가 있습니다.");
                }
            }
            if (result.transcriptionJobStatus().equals(TranscriptionJobStatus.FAILED)) {
                throw new IllegalArgumentException("Transcribe 과정에서 오류가 발생했습니다.");
            }
        }
    }
}

package sparta.cloudarchitecture.s3.service;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.time.Duration;
import java.util.UUID;


@Slf4j
@Service
@Profile("prod")
@RequiredArgsConstructor
public class S3Service {

    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;


    // 파일 업로드
    @Transactional
    public String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String key = "uploads/" + UUID.randomUUID() + "_" + originalFilename;

        try {
            s3Template.upload(bucketName, key, file.getInputStream());
            log.info("[S3] File uploaded successfully: {}", key);
            return key;
        } catch (IOException e) {
            log.error("[S3] File upload failed: {}", key, e);
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }

    // Presigned URL 생성
    @Transactional(readOnly = true)
    public URL getDownloadUrl(String key) {
        log.info("[S3] Generate presigned URL for key: {}", key);

        // 유효기간 7일로 설정
        Duration expiration = Duration.ofDays(7);

        URL presignedUrl = s3Template.createSignedGetURL(bucketName, key, expiration);

        log.info("[S3] Presigned URL 생성 (유효기간 7일): {}", presignedUrl);
        return presignedUrl;
    }
}

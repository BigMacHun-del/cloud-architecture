package sparta.cloudarchitecture.s3.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sparta.cloudarchitecture.s3.dto.FileDownloadUrlResponse;
import sparta.cloudarchitecture.s3.dto.FileUploadResponse;
import sparta.cloudarchitecture.s3.service.S3Service;

import java.net.URL;

@RestController
@RequiredArgsConstructor
@Profile("prod")
public class FileController {

    private final S3Service s3Service;

    @PostMapping("/files/upload")
    public ResponseEntity<FileUploadResponse> upload(@RequestParam("file") MultipartFile file) throws FileUploadException {
        String key = s3Service.upload(file);
        return ResponseEntity.ok(new FileUploadResponse(key));
    }

    @GetMapping("/files/download-url")
    public ResponseEntity<FileDownloadUrlResponse> getDownloadUrl(@RequestParam String key) {
        URL url = s3Service.getDownloadUrl(key);
        return ResponseEntity.ok(new FileDownloadUrlResponse(url.toString()));
    }
}

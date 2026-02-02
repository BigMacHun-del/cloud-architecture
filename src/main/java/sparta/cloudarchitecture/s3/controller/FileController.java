package sparta.cloudarchitecture.s3.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sparta.cloudarchitecture.domain.members.service.MemberService;
import sparta.cloudarchitecture.s3.dto.FileDownloadUrlResponse;
import sparta.cloudarchitecture.s3.dto.FileUploadResponse;
import sparta.cloudarchitecture.s3.service.S3Service;

import java.net.URL;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Profile("prod")
public class FileController {

    private final S3Service s3Service;
    private final MemberService memberService;

    /**
     * 프로필 이미지 업로드
     * POST /api/members/{id}/profile-image
     * - S3에 이미지 업로드 후 DB에 URL 저장
     */
    @PostMapping("/{id}/profile-image")
    public ResponseEntity<FileUploadResponse> upload(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) throws FileUploadException {
        // S3에 파일 업로드
        String key = s3Service.upload(file);

        // DB에 이미지 키 업데이트
        memberService.updateProfileImage(id, key);

        return ResponseEntity.ok(new FileUploadResponse(key));
    }

    /**
     * 프로필 이미지 다운로드 URL 생성
     * GET /api/members/{id}/profile-image
     * - DB에서 이미지 key 조회 후 Presigned URL 생성 (유효기간 7일)
     */
    @GetMapping("/{id}/profile-image")
    public ResponseEntity<FileDownloadUrlResponse> getDownloadUrl(@PathVariable Long id) {
        // DB에서 회원의 프로필 이미지 key 조회
        String key = memberService.getProfileImageKey(id);

        // Presigned URL 생성 (유효기간 7일)
        URL url = s3Service.getDownloadUrl(key);
        memberService.updateUrl(id, url);

        return ResponseEntity.ok(new FileDownloadUrlResponse(url.toString()));
    }
}

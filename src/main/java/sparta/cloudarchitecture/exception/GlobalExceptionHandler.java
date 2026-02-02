package sparta.cloudarchitecture.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //IllegalArgumentException(팀원 찾을 수 없을 때) 에러 시 로깅
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletRequest request
    ) {
        log.error(
                "[API-ERROR] {}",
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(
            Exception e,
            HttpServletRequest request
    ) {

        log.error(
                "[API-ERROR] method={} uri={} message={}",
                request.getMethod(),
                request.getRequestURI(),
                e.getMessage(),
                e
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버 오류가 발생했습니다.");
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileException(
            FileUploadException e,
            HttpServletRequest request
    ) {
        log.error(
                "[API-ERROR] {}",
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}

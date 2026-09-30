package api.iocpthb260515_nguyentrungcu.exception;

import api.iocpthb260515_nguyentrungcu.dto.response.ApiResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Builder
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException (ResourceNotFoundException e) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message("Resource not found")
                .success(false)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<?>> handleDuplicateResourceException (DuplicateResourceException e) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.CONFLICT.value())
                .message("Resource duplicate")
                .success(false)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message("Bad Request")
                .success(false)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException (Exception e) {
        log.error(e.getMessage());
        ApiResponse<String> response = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal server error")
                .success(false)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

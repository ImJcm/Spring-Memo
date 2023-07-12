package com.example.springmemo.exception;

import com.example.springmemo.dto.ApiResponseDto;
import com.google.protobuf.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
    모든 Controller의 예외처리 핸들링
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*@ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponseDto> illegalArgumentException(IllegalArgumentException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.BAD_REQUEST
        );
    }*/

    @ExceptionHandler({AdminTokenWrongException.class})
    public ResponseEntity<ApiResponseDto> adminTokenWrongException(AdminTokenWrongException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ApiResponseDto> userNotFoundException(UserNotFoundException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({MemoNotFoundException.class})
    public ResponseEntity<ApiResponseDto> memoNotFoundException(MemoNotFoundException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(),null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({MemoOwnerNotException.class})
    public ResponseEntity<ApiResponseDto> memoOwnerNotException(MemoOwnerNotException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler({CommentNotFoundException.class})
    public ResponseEntity<ApiResponseDto> commentNotFoundException(CommentNotFoundException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({CommentOwnerNotException.class})
    public ResponseEntity<ApiResponseDto> commentOwnerNotException(CommentOwnerNotException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.UNAUTHORIZED
        );
    }

}

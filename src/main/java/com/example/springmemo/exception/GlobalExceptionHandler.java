package com.example.springmemo.exception;

import com.example.springmemo.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/*
    모든 Controller의 예외처리 핸들링
 */
//RestControllerAdvice = @ControllerAdvice + @ResponseBody
@ControllerAdvice
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
    @ResponseBody
    public ResponseEntity<ApiResponseDto> adminTokenWrongException(AdminTokenWrongException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler({DuplicateUsernameException.class})
    @ResponseBody
    public ResponseEntity<ApiResponseDto> duplicateUsernameException(DuplicateUsernameException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(400,ex.getMessage(),null);
        return new ResponseEntity<> (
                apiResponseDto,
                HttpStatusCode.valueOf(400)
        );
    }
    @ExceptionHandler({SignupValidationException.class})
    @ResponseBody
    public ResponseEntity<ApiResponseDto> signupValidationException(SignupValidationException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(400,ex.getMessage(),ex.getErrorMap());
        return new ResponseEntity<> (
                apiResponseDto,
                HttpStatusCode.valueOf(400)
        );
    }
    /*@ExceptionHandler({AdminTokenWrongException.class, DuplicateUsernameException.class, SignupValidationException.class})
    public String SignupException() {
        return "redirect:/api/user/signup?error";
    }*/



    @ExceptionHandler({MemoNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ApiResponseDto> memoNotFoundException(MemoNotFoundException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(),null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({MemoOwnerNotException.class})
    @ResponseBody
    public ResponseEntity<ApiResponseDto> memoOwnerNotException(MemoOwnerNotException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler({CommentNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ApiResponseDto> commentNotFoundException(CommentNotFoundException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({CommentOwnerNotException.class})
    @ResponseBody
    public ResponseEntity<ApiResponseDto> commentOwnerNotException(CommentOwnerNotException ex) {
        ApiResponseDto apiResponseDto = new ApiResponseDto(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
        return new ResponseEntity<>(
                apiResponseDto,
                HttpStatus.UNAUTHORIZED
        );
    }
}

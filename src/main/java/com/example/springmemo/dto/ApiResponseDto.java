package com.example.springmemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto {
    private String message;
    private int status;
    private Object data;

    public ApiResponseDto(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

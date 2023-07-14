package com.example.springmemo.exception;


import lombok.Getter;

import java.util.Map;
@Getter
public class SignupValidationException extends RuntimeException{
    private Map<String,String> errorMap;

    public SignupValidationException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }
}

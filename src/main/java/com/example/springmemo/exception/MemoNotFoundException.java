package com.example.springmemo.exception;

public class MemoNotFoundException extends RuntimeException {
    public MemoNotFoundException(String message) {super(message);}
}

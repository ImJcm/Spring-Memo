package com.example.springmemo.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String message) {super(message);}
}
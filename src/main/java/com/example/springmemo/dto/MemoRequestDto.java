package com.example.springmemo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private String password;
}

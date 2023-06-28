package com.example.springmemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDto {
    private Long memoId;
    private String title;
    private String username;
    private String contents;

    //
    /*public MemoRequestDto(String title, String username, String contents) {
        this.title = title;
        this.username = username;
        this.contents = contents;
    }*/

}

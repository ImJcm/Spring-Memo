package com.example.springmemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long commentId;
    private Long memoId;
    private String username;
    private String comments;
}

package com.example.springmemo.dto;

import com.example.springmemo.entity.Memo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long commentId;
    private Long memoId;
    //private Memo memo;
    private String username;
    private String comments;
}

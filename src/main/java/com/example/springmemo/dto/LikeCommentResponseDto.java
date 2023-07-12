package com.example.springmemo.dto;

import com.example.springmemo.entity.LikeComment;
import lombok.Getter;

@Getter
public class LikeCommentResponseDto {
    private Long userId;
    private Long commentId;

    public LikeCommentResponseDto(LikeComment likeComment) {
        this.userId = likeComment.getUser().getUserId();
        this.commentId = likeComment.getComment().getCommentId();
    }
}

package com.example.springmemo.dto;

import com.example.springmemo.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private Long memoId;
    private String username;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.memoId = comment.getMemoId();
        this.username = comment.getUsername();
        this.comments = comment.getComments();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}

package com.example.springmemo.dto;

import com.example.springmemo.entity.Comment;
import com.example.springmemo.entity.Memo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long commentId;
    private Long memoId;
    //private Memo memo;
    private String username;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    /* 댓글의 좋아요 개수 */
    private int likes;
    /*
        또는 responseDto에 String username, Long memoId가 아닌
        Memo, User 객체를 반환하여 client 측에서 변환하여 사용해도 됨
     */
    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.memoId = comment.getMemo().getMemoId();
        this.username = comment.getUser().getUsername();
        this.comments = comment.getComments();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.likes = comment.getLikeCommentList().size();
    }
}

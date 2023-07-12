package com.example.springmemo.dto;

import com.example.springmemo.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {
    private Long memoId;
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    /* 게시글의 좋아요 개수 */
    private int likes;
    public MemoResponseDto(Memo memo) {
        this.memoId = memo.getMemoId();
        this.title = memo.getTitle();
        //this.username = memo.getUsername();
        this.username = memo.getUser().getUsername();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.likes = memo.getLikeMemoList().size();
    }
}

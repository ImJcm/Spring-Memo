package com.example.springmemo.dto;

import com.example.springmemo.entity.LikeMemo;
import lombok.Getter;

@Getter
public class LikeMemoResponseDto {
    private Long userId;
    private Long memoId;

    public LikeMemoResponseDto(LikeMemo likeMemo) {
        this.userId = likeMemo.getUser().getUserId();
        this.memoId = likeMemo.getMemo().getMemoId();
    }
}

package com.example.springmemo.controller;

import com.example.springmemo.dto.ApiResponseDto;
import com.example.springmemo.dto.CommentRequestDto;
import com.example.springmemo.dto.CommentResponseDto;
import com.example.springmemo.security.UserDetailsImpl;
import com.example.springmemo.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/Comments/{memoId}")
    public List<CommentResponseDto> getComments(@PathVariable Long memoId) {
        return commentService.getComments(memoId);
    }

    @PostMapping("/Comment/{memoId}")
    public CommentResponseDto createComment(@PathVariable Long memoId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(memoId,commentRequestDto, userDetails);
    }

    @PutMapping("/Comment/{memoId}/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long memoId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(memoId, commentId, commentRequestDto, userDetails);
    }

    @DeleteMapping("/Comment/{memoId}/{commentId}")
    public String deleteComment(@PathVariable Long memoId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(memoId, commentId, userDetails);
    }

    @GetMapping("/Comment/{commentId}/likes")
    public ResponseEntity<ApiResponseDto> getlikesComment(@PathVariable Long commentId) {
        return commentService.getlikescomment(commentId);
    }

    @PostMapping("/Comment/{commentId}/like")
    public ResponseEntity<ApiResponseDto> likeComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.likeComment(commentId,userDetails);
    }
}

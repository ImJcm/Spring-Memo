package com.example.springmemo.repository;

import com.example.springmemo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //List<Comment> findAllByMemoIdOrderByCreatedAtDesc(Long memoId);
    List<Comment> findAllByMemo_MemoIdOrderByCreatedAtDesc(Long memoId);

    //Optional<Comment> findByMemoIdAndCommentId(Long memoId, Long commentId);
    Optional<Comment> findByMemo_MemoIdAndCommentId(Long memoId, Long commentId);
}

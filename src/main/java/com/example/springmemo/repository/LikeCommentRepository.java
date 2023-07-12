package com.example.springmemo.repository;

import com.example.springmemo.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeCommentRepository extends JpaRepository<LikeComment,Long> {

    List<LikeComment> findAllByComment_CommentId(Long commentId);

    Optional<LikeComment> findByComment_CommentIdAndUser_UserId(Long commentId, Long userId);
}

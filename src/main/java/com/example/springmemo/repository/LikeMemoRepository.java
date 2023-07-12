package com.example.springmemo.repository;

import com.example.springmemo.entity.LikeMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeMemoRepository extends JpaRepository<LikeMemo,Long> {
    List<LikeMemo> findAllByMemo_MemoId(Long memoId);

    Optional<LikeMemo> findByMemo_MemoIdAndUser_UserId(Long memoId, Long userId);
}

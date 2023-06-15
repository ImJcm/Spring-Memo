package com.example.springmemo.repository;

import com.example.springmemo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo,Long> {
    List<Memo> findAllByOrderByCreatedAtDesc();
}

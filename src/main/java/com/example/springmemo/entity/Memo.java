package com.example.springmemo.entity;

import com.example.springmemo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="memoproject")
@NoArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "password", nullable = false)
    private String password;

    public Memo(MemoRequestDto memoRequestDto) {
        this.title = memoRequestDto.getTitle();
        this.username = memoRequestDto.getUsername();
        this.contents = memoRequestDto.getContents();
        this.password = memoRequestDto.getPassword();
    }

    public void update(MemoRequestDto memoRequestDto) {
        this.title = memoRequestDto.getTitle();
        this.username = memoRequestDto.getUsername();
        this.contents = memoRequestDto.getContents();
        this.password = memoRequestDto.getPassword();
    }
}

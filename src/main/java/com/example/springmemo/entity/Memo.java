package com.example.springmemo.entity;

import com.example.springmemo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="memoproject")  //객체를 DB와 Mapping, Table 지정
@NoArgsConstructor
public class Memo extends Timestamped {

    @Id //고유 primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //테이블 값 생성 시, 자동으로 id 증가 = auto increment
    private Long memoId;

    @Column(name = "title", nullable = false)   //테이블 필드 = column
    private String title;

    /*@Column(name = "username", nullable = false)
    private String username;*/

    @Column(name = "contents", nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "memo",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "memo",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LikeMemo> likeMemoList = new ArrayList<>();

    public Memo(MemoRequestDto memoRequestDto) {
        this.title = memoRequestDto.getTitle();
        //this.username = memoRequestDto.getUsername();
        this.contents = memoRequestDto.getContents();
    }

    public void update(MemoRequestDto memoRequestDto) {
        this.title = memoRequestDto.getTitle();
        //this.username = memoRequestDto.getUsername();
        this.contents = memoRequestDto.getContents();
    }
}

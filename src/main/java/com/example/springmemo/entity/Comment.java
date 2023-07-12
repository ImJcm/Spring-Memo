package com.example.springmemo.entity;

import com.example.springmemo.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="comments")  //객체를 DB와 Mapping, Table 지정
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id //고유 primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //테이블 값 생성 시, 자동으로 id 증가 = auto increment
    private Long commentId;

    /*@Column(name="memo_id",nullable = false)
    private Long memoId;*/

    /*@Column(name = "username", nullable = false)
    private String username;*/

    @Column(name = "comments", nullable = false)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    //@JoinColumn(insertable = false, updatable = false)
    @JoinColumn(name="memo_id")
    private Memo memo;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LikeComment> likeCommentList = new ArrayList<>();

    public Comment(CommentRequestDto commentRequestDto) {
        //this.memoId = commentRequestDto.getMemoId();
        //this.username = commentRequestDto.getUsername();
        this.comments = commentRequestDto.getComments();
    }

    public void update(CommentRequestDto commentRequestDto) {
        //this.memoId = commentRequestDto.getMemoId();
        //this.username = commentRequestDto.getUsername();
        this.comments = commentRequestDto.getComments();
    }
}

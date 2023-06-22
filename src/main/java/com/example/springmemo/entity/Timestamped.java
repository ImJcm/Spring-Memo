package com.example.springmemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우, createdAt, modeifiedAt 처럼 추상 클래스에 선언한 멤버변수를 컬럼으로 인식 가능
@EntityListeners(AuditingEntityListener.class)  //해당 클래스에 JPA Auditing 기능을 포함시킨다.
public abstract class Timestamped {
    //Spring Data JPA에서는 시간에 대해서 자동으로 값을 넣어주는 기능 = JPA Auditing을 제공
    @CreatedDate    //데이터 생성일자
    @Column(updatable = false)  //한번 생성 시, 값 수정 불가 설정
    @Temporal(TemporalType.TIMESTAMP)   //날자 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    private LocalDateTime createdAt;

    @LastModifiedDate   //데이터 수정일자
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}
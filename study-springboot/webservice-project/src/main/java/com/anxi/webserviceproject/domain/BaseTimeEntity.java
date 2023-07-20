package com.anxi.webserviceproject.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// Entity 생성/수정시간'
@Getter
@MappedSuperclass // Entity클래스의 Auditing 기능(상위 클래스의 기능)을 사용한다는 어노테이션
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 추가
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;



}

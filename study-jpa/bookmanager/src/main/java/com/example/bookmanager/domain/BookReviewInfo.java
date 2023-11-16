package com.example.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class BookReviewInfo extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    // 1 : 1 연관관계 매핑
    @OneToOne(optional = false) // Book은 절대 존재해야함
    private Book book; // DDL에서는 Book테이블의 id를 가져옴

    private float averageReviewScore;

    private int reviewCount;

    // wrapper이 아닌 primitive로 선언한 이유
    // 평점과 리뷰 수의 기본 값을 0으로 하기 위해 (만약 null을 기본값으로 지정할거면 wrapper로 지정)
}


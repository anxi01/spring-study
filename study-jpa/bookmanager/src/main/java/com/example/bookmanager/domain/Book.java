package com.example.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본은 auto
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    private Long publisherId;

    @OneToOne(mappedBy = "book") // mappedBy를 통해 Book에는 BookReviewInfo의 FK가 존재하지 않는다.
    @ToString.Exclude // ToString 순환 참조가 발생하기 때문에 배제해준다.
    private BookReviewInfo bookReviewInfo;
}


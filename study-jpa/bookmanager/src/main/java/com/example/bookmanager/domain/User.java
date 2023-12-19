package com.example.bookmanager.domain;


import com.example.bookmanager.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor// (force = true, access = AccessLevel.PROTECTED)  // jpa에서는 NoArgsConstructor 무조건 필요
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity // pk 필요
@EntityListeners(value = UserEntityListener.class )
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 증가
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    // 엔티티가 어떤 컬럼으로 조인을 하게 될지 지정해주는 어노테이션
    // User 엔티티에서 UserHistory를 저장하거나 수정하지 못하게 함
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();
    // Persist하기 전에 userHistories는 null이기 때문에 NullPointerException 발생 가능
    // 따라서 new ArrayList<>(); 생성해음

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}

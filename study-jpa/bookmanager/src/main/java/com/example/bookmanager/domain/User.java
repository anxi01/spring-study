package com.example.bookmanager.domain;


import com.example.bookmanager.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;

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

}

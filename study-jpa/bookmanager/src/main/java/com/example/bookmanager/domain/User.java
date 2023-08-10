package com.example.bookmanager.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)  // jpa에서는 NoArgsConstructor 무조건 필요
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Data
@Builder
@Entity // pk 필요
public class User {

    @Id
    @GeneratedValue//자동으로 증가
    private Long id;
    
    @NonNull
    private String name;

    @NonNull
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //@OneToMany(fetch = FetchType.EAGER)
    //private List<Address> address;

}

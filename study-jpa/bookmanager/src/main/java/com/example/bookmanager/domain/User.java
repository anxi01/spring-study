package com.example.bookmanager.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)  // jpa에서는 NoArgsConstructor 무조건 필요
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

}

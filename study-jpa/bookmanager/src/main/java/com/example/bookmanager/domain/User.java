package com.example.bookmanager.domain;


import com.example.bookmanager.domain.listener.Auditable;
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
//@Table(name = "user", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User extends BaseEntity implements Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 증가
    private Long id;
    
    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    /*@Column(updatable = false)
    @CreatedDate // 자동으로 시간 값을 지정
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate // 자동으로 시간 값을 지정
    private LocalDateTime updatedAt;*/

/*    @Transient // 영속성 처리 제외 -> DB 데이터에 반영 X
    private String testData;*/

/*    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;*/

    /*@PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }*/
}

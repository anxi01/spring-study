package com.example.filter.dto;

import lombok.*;

@Data // Getter, Setter, toString 등등 모든 오버라이드 함수 만들어줌
@NoArgsConstructor // default con
@AllArgsConstructor
public class User {

    private String name;
    private int age;
}

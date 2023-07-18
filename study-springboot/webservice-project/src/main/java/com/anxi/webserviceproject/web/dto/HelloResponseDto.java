package com.anxi.webserviceproject.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 변수의 생성자 생성
@Getter
public class HelloResponseDto {

    private final String name;
    private final int amount;

}

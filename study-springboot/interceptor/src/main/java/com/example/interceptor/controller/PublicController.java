package com.example.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    // 모든 사용자들이 쓸 수 있는 api

    @GetMapping("/hello")
    public String hello() {
        return "public hello";
    }
}

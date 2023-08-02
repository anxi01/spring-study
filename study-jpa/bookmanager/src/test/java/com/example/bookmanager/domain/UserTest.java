package com.example.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UserTest {

    @Test
    void test(){
        User user = new User();
        user.setName("Han");
        user.setEmail("abc@naver.com");

        User user1 = new User(null, "han", "tjdals", LocalDateTime.now(), LocalDateTime.now());
        System.out.println(">>> " + user);
        System.out.println(">>> "+user1);

        User user2 = User.builder().name("han").email("abc").build();
        System.out.println(user2);
    }
}
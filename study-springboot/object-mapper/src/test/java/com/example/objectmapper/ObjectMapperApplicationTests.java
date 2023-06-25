package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {

        // Text JSON -> Object
        // Object -> Text JSON

        // controller request json(text) -> object
        // response object -> json(text)

        var objectMapper = new ObjectMapper();

        // object -> text
        // objectMapper가 getter를 활용한다.
        var user = new User("han", 23, "010-1234-5678");

        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // text -> object
        // objectMapper가 default 생성자를 활용한다.
        var objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);
    }


}

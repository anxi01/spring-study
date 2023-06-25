package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    // 현재 사용 방법
    @GetMapping(path = "/hello") //http://localhost:8080/api/get/hello
    public String Hello(){
        return "get Hello";
    }
    // 이전 사용 방법
    @RequestMapping(path = "/hi", method = RequestMethod.GET) // get, post, put, delete 등 모든 메소드가 모두 작동
    public String Hi(){
        return "get Hi";
    }

    /** pathVariable : 변화하는 값 */
    // http://localhost:8080/api/get/path-variable/{name}
    // @GetMapping의 { }와 매개변수의 이름이 같아야 하
    @GetMapping("/path-variable/{name}")
    //public String pathVariable(@PathVariable String name){
    public String pathVariable(@PathVariable(name = "name") String pathName){
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    /** QueryParameter */
    // 검색을 할 때 매개변수의 인자(?부터 시작해서 &로 끊어짐)
    // ?key=value&key2=value2 ...
    @GetMapping(path = "/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });
        return sb.toString();
    }

    // queryparam의 타입을 명시적으로 작성할 수 있다.
    @GetMapping("/query-param02")
    public String queryParam02(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " + email + " " + age;
    }


    // 가장 많이 사용하는 방법 -> dto 사용
    // @RequestParam 사용 X -> Spring에서 저절로 key와 value를 매치해줌
    @GetMapping("/query-param03")
    public String queryParam03(UserRequest userRequest){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}

package com.example.bookmanager.repository;

import com.example.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud(){
        /** Read
         * List<User> users = userRepository.findAll(); 전체 조회
         * List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name")); 이름의 알파벳 역순으로 출력
         * List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L)); id가 1,3,5인 것들만 조회
         * users.forEach(s -> System.out.println(s));
         */

        /**
         * User user1 = new User("jack", "jack@com");
         * User user2 = new User("steve", "steve@com");
         *
         * userRepository.saveAll(Lists.newArrayList(user1, user2));
         * List<User> users = userRepository.findAll();
         * users.forEach(System.out::println);
         */

        /**
         * Optional<User> user = userRepository.findById(1L);
         * User user = userRepository.findById(1L).orElse(null);
         * System.out.println(user);
         */

        /**
         *  userRepository.save(new User("new han", "newHan@com"));
         *  userRepository.flush();
         *
         *  userRepository.saveAndFlush(new User("new han", "newHan@com"))
         *  userRepository.findAll().forEach(System.out::println);
         */

        /**
         * long count = userRepository.count();
         * System.out.println(count);
         *
         * boolean exists = userRepository.existsById(1L);
         * System.out.println(exists);
         */

        /**
         * userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
         * userRepository.deleteById(1L);
         * userRepository.deleteAll();
         *
         * userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
         */

        /**
         * // 대용량 삭제시 deleteInBatch를 사용한다. -> delete 쿼리를 단 한번만 실행한다.
         * userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
         * userRepository.deleteAllInBatch();
         */

        /**
         * // 페이징
         * Page<User> users = userRepository.findAll(PageRequest.of(1, 3));  // page는 0부터 시작 -> 0,1 총 2장 -> 2번째 장을 보여줌
         * System.out.println("page : " + users); // 5개의 user가 있으니 총 2장 -> 1page : 3users, 2page : 2users
         * System.out.println("totalElements : " + users.getTotalElements());
         * System.out.println("totalPages : " + users.getTotalPages());
         * System.out.println("numberOfElements : " + users.getNumberOfElements());
         * System.out.println("sort : " + users.getSort());
         * System.out.println("size : " + users.getSize());
         *
         * users.getContent().forEach(System.out::println);
         */

        /**
        // QueryByExample
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") // 이름은 무시하겠다.
                .withMatcher("email", endsWith()); // email만 매칭시키겠다.
        Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);
        userRepository.findAll(example).forEach(System.out::println);
         */

        userRepository.save(new User("david", "david@fastcampus.com"));
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("david-updated@fastcampus.com");

        userRepository.save(user);
    }

}
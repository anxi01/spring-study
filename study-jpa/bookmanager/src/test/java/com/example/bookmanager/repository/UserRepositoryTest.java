package com.example.bookmanager.repository;

import com.example.bookmanager.domain.Gender;
import com.example.bookmanager.domain.User;
import com.example.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

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
         * // 페이징 : 게시판의 페이지 수
         Page<User> users = userRepository.findAll(PageRequest.of(1, 3));  // page는 0부터 시작 -> 0,1 총 2장 -> 2번째 장을 보여줌
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
        // QueryByExample : QBE
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name") // 이름은 무시하겠다.
                .withMatcher("email", endsWith()); // email만 매칭시키겠다.
        Example<User> example = Example.of(new User("martin", "fastcampus.com"), matcher);
        userRepository.findAll(example).forEach(System.out::println);
         */

        /**
         // Update
         * userRepository.save(new User("david", "david@fastcampus.com"));
         * User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
         * user.setEmail("david-updated@fastcampus.com");
         *
         * userRepository.save(user);
         */

    }
    @Test
    void select(){
        /*
        System.out.println(userRepository.findByName("dennis"));
        System.out.println("findByEmail : " + userRepository.findByEmail("martin@fastcampus.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("martin@fastcampus.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("martin@fastcampus.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("martin@fastcampus.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("martin@fastcampus.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("martin@fastcampus.com"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("martin@fastcampus.com"));

        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("martin"));
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));

        System.out.println("findFirst2ByName : " + userRepository.findFirst2ByName("martin"));
        System.out.println("findTop2ByName : " + userRepository.findTop2ByName("martin"));
        */

        // And Or
        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com", "martin"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastcampus.com", "dennis"));

        // After : 특정 param 보다 큰 것 -> 보통 날짜나 시간에 사용  (반대는 Before)
        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));

        // After과 기능은 같지만 범용적으로 넓게 사용 가능(또한 등호 사용 가능)
        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        // Between (양 끝 숫자를 포함)
        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(2L, 4L)); // 2, 3, 4
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(2L, 4L)); // 2, 3, 4

        // IsNotNull, IsNotEmpty
        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
        // System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty()); // IsEmpty / IsNotEmpty can only be used on collection properties!

        // In
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin", "dennis")));

        // Starting, Ending, contains
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%art%")); // mar% / %tin

        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar")); // 처음
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin")); // 끝
        System.out.println("findByNameContains : " + userRepository.findByNameContains("art")); // 중간
    }
    @Test
    void pagingAndSortingTest(){
        // 정렬

        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
        // findLast1ByName(String name) 오류 해결 + Top1 == Top
        System.out.println("findTop1ByNameOrderByIdDesc : " + userRepository.findTop1ByNameOrderByIdDesc("martin"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
        System.out.println("findFirstByNameWithSortParams : "
                + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));

        // 페이징
        System.out.println("findByNameWithPaging : " +
                userRepository.findByName("martin", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
        System.out.println("findByNameWithPaging : " +
                userRepository.findByName("martin", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getTotalElements());
    }

    @Test
    void insertAndUpdateTest(){

        User user = new User("martin", "martin2@naver.com");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrrrrrr");

        userRepository.save(user2);
    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest(){
        User user = new User();
        user.setEmail("han@abc.com");
        user.setName("han");
        // insert 발생
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("hana");
        // update 발생
        userRepository.save(user2);

        // delete 발생
        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("han@abc.com");
        user.setName("han");
        //user.setCreatedAt(LocalDateTime.now());
        //user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("han@abc.com"));
    }
    @Test
    void preUpdateTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is : " + user);

        user.setName("han22");
        userRepository.save(user);

        System.out.println("to-be : " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setEmail("han@abc.com");
        user.setName("han");

        // insert
        userRepository.save(user);

        // update
        user.setName("New Han");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userRelationTest() {
        User user = new User();
        user.setName("anxi");
        user.setEmail("anxi@com");
        user.setGender(Gender.MALE);
        userRepository.save(user);

        // 2번의 Update
        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@com");
        userRepository.save(user);

//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("daniel@com").getId()
//        );

        // 연관관계 매핑을 통해 위 코드를 아래와 같이 바꿀 수 있음
        List<UserHistory> result = userRepository.findByEmail("daniel@com").getUserHistories();

        result.forEach(System.out::println);
    }
}
package com.example.bookmanager.repository;

import com.example.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    User findFirst1ByName(String name);
    User findTop1ByName(String name);

    List<User> findFirst2ByName(String name);
    List<User> findTop2ByName(String name);

    // User findLast1ByName(String name); 오류 발생
}

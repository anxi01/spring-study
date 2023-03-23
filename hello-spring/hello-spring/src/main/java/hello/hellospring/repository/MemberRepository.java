package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // Member 저장
    Member save(Member member);

    // Member Id, Name 찾기
    // Optional을 사용하면 id나 name이 null일경우 null이 포함돼서 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // 전체 데이터 찾기
    List<Member> findAll();
}

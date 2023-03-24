package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // HashMap은 key를 통해 value를 얻는다.
    private static Map<Long, Member> store = new HashMap<>();
    // key 값 생성
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //멤버의 Id 설정
        member.setId(++sequence);
        //store에 id 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        /** Map에서 value를 돌면서(stream)
        만약 member의 이름이 매개변수의 이름과 같다면 그중 하나를 리턴한다**/
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}

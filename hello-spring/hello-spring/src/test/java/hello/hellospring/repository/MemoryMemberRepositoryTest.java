package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  /** 하나의 테스트 함수가 끝날 때마다 AfterEach가 실행된다.*/
    public void afterEach(){
        repository.clearStore(); //테스트 함수 실행 후 데이터가 저장되는 것을 clear한다.
    }

    @Test //TestCode 실행 애노테이션
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional이기 때문에 get으로 꺼낸다.

        Member result = repository.findById(member.getId()).get();

        /** result와 member가 같은지 확인하는 방법 **/
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}

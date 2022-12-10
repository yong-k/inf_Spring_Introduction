package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// ** 테스트는 각각 독립적으로 실행되어야 한다.
// ** 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
//==> 테스트 하나 끝날 때마다 공용 데이터 싹 지워줘야 한다.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트 끝날 때마다 메모리 DB에 저장된 데이터 삭제해줌
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // 테스트해 볼 메서드 만들고, @Test 어노테이션 붙여주면 됨
    // @Test → org.junit.jupiter.api.Test;
    // 그리고, 그냥 옆에 실행 누르면, 해당 메서드 실행된다.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // 반환타입 Optional → get()으로 꺼냄
        Member result = repository.findById(member.getId()).get();

        // 결과 확인
        // Assertions → org.junit.jupiter.api
        //Assertions.assertEquals(member, null);
        //-- 다르면 다르다고 에러 뜸
        //Assertions.assertEquals(member, result);
        //-- 실제 값과 기댓값이 같으면 녹색불 들어옴

        //-- 요새는 아래 방법을 더 많이 씀 (조금 더 편함)
        // Assertions → org.assertj.core.api (static으로 import 했음)
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);
        //--member가 result와 똑같아? (바로 읽힘)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // findByName() 이 잘 동작하는지 테스트
        Member result = repository.findByName("spring1").get();
        //-- get() 사용하면 Optional 한 번 까서 꺼낼 수 있음

        assertThat(result).isEqualTo(member1);  // test 통과
        //assertThat(result).isEqualTo(member2);  // test 불통
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        //assertThat(result.size()).isEqualTo(3);
        assertThat(result.size()).isEqualTo(2);
    }
}

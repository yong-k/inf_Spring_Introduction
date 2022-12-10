package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService();

    // clear해줘야 되는데 MemoryMemberRepository 에 있으니까 객체 생성
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //-- 현재 MemberService.java에서 만든 memberRepository와
    //        MemberServiceTest.java에서 만든 memberRepository 서로 다른 인스턴스이다.
    // 같은 걸로 test 해야 맞는데, 다른 repository를 사용해서 테스트해보고 있는 것이다.
    // 같은 인스턴스를 쓰도록 바꾸기 위해,
    // MemberSerice.java로 가서 new() 연산 없애는 방식으로 코드 수정 ㄱㄱ

    // 고치고 와서, 동작하기 전에 MemberService 객체 만들 때, 넣어주면 됨
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // DI(Dependency Injection)
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 외국 사람들하고 일하는거 아니라면, 테스트할 때는 한글로 이름해도 상관 없음
    @Test
    //void join() {
    void join() {
        // given-when-then 추천
        // 테스트는,
        // 뭔가가 주어져서(given), 이걸 실행했을 때(when), 결과가 이렇게 나와야 한다.(then)

        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void exceptDuplicateMember() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        /*
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        // 이거 때문에 try ~ catch 넣는거 애매함
        // 그래서 좋은 문법 제공해준다.
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //--람다식의 로직 실행하면, IllegalStateException 예외 발생해야 한다는 의미

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // 회원 서비스를 만들려면, 먼저 회원 리포지토리가 있어야 한다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //-- MemberServiceTest.java와 같은 인스턴스 사용하기 위해 코드 수정
    //   직접 new()로 내가 넣어주는 게 아니라, 외부에서 넣어주는 방식으로 코드 수정
    //--> DI(Dependency Injection)
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 안된다는 정책이 있다고 가정
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //-- 이렇게 바로 Optional로 반환하는 거 좋지 않음. 예쁘지도 않고

        // 이렇게 해서 그냥 바로 아래에 .ifPresent() 등 필요한 거 붙여서 쓸 수 있음
        /*
        memberRepository.findByName(member.getName())
                // null이 아니고 어떤 값이 있으면 동작
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        */
        //-- findByName으로 로직이 쭉 나오는 상황
        //   이런 경우에는 메서드로 따로 뽑는 것이 좋다.
        //   ==> 단축키: ctrl + p
        validateDuplicateMember(member);    // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                // null이 아니고 어떤 값이 있으면 동작
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * Id로 회원 찾기
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

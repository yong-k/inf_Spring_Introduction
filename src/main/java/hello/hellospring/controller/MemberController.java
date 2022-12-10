package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller,@Service,@Repository 어노테이션 있으면, 스프링 빈으로 자동 등록된다.
// 그 때 MemberController의 생성자 호출함
// 그런데, 생성자에 @Autowired 어노테이션 있으면,
// Spring이 스프링 컨테이너에 있는 MemberSerivce를 가져다가 연결시켜줌
// +) 이 때, MemberService는 스프링 컨테이너에 Bean으로 등록되어 있어야 한다.
//    → MemberService에 @Service 어노테이션 붙어있으면 됨
@Controller
public class MemberController {
    // DI-1) 필드 주입 (인텔리제이에서 권장하지 않음)
    //       중간에 바꿀 수 있는 방법이 아예 없음
    //@Autowired private MemberService memberService;

    // DI-2) Setter 주입
    //-- 단점: 누군가가 MemberController를 호출했을 때 setXxx가 public으로 열려있어야 함
    //        setMemberService를 한 번 setting되면 중간에 바꿀 일이 없는데
    //        얘가 public하게 노출되어 있는 상태가 되어버린다.
    //        잘못 바꾸면 문제 생겨버림
    //→ 의존관계가 실행 중에 동적으로 변하는 경우는 거의,,아예,,! 없으므로 생성자 주입을 권장!
    /*
    private MemberService memberService;     // 'final' 빼야한다.

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */

    // DI-3) 생성자 주입 (권장!)
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

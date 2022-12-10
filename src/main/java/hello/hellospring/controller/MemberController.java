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
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

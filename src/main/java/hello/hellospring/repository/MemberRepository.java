package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional<T>: java 8 에 추가된 기능
    // findById나 findByName에서 없으면 null로 반환됨
    // null을 처리하는 방법 중에서 Optional로 감싸서 반환하는 것
    Optional<Member> findById(long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

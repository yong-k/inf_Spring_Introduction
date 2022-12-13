package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
    //-- 구현할 거 없이 이렇게하면 다 만든 것
    // JpaReposity 타고 또 타고 들어가서 보면 다 만들어진 거 확인할 수 있음
    // findBy~~ 이런식으로 메서드 이름 지으면
    // JPQL: select m from Member m where m.name = ?
    // 이런식으로 쿼리를 작성해준다.
    // findByNameAndId(String name, Long Id) 이런식으로 해도 다 검색됨 ㅎ!

    // 페이징 기능도 자동으로 제공된다.
}

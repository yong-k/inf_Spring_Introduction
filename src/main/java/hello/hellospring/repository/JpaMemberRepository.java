package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA는 EntityManager라는 걸로 모든게 동작함
    private final EntityManager em;

    // jpa 라이브러리 받으면, SpringBoot가 자동으로 EntityManager 생성해줌
    // 우린 만들어진 걸 Injection 받으면 된다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist: 영구저장하다.
        em.persist(member);
        return member;

        //-- 이렇게만 하면 끝남
        //   jpa가 insert쿼리 다 만들어서 DB에 집어넣고,
        //   setId까지 해줌
    }

    @Override
    public Optional<Member> findById(long id) {
        // find(조회할 타입, 식별자 PK값)
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // PK 값 아니고 다른 걸로 조회하려면, JPQL이라는 객체지향 쿼리 언어 써야한다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // "select m from Member m" → JPQL 쿼리 언어
        // 우리가 보통 테이블 단위로 쿼리를 날리는 데, 객체를 단위로 쿼리를 날리는 것
        // Member entity를 조회해서 Member 객체 자체를 SELECT(별칭을 m이라고 한 것)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}

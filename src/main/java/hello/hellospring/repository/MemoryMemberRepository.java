package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        //return store.get(id);
        //-- 결과가 없으면 null 이 반환될 것임

        // Optional.ofNullable(): 값이 null 일 수도 있고, 아닐수도 있음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // stream 돌림
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();     // 하나 찾아지면 그거 반환함, 없으면 Optional에 null 포함되어 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store 싹 비우는 역할
    public void clearStore() {
        store.clear();
    }
}

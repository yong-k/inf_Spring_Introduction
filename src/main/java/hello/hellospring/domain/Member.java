package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 현재 Id PK에는 DB에서 알아서 생성해주고 있는 상태이다.
    //-- DB가 알아서 생성해주는 거 → Identity
    private Long id;

    // 만약 DB에 컬럼명이 username이라면
    //@Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

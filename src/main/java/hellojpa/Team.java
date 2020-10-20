package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity
@Getter
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // Member Entity의 team 필드에 의해 매핑되어있다.
    private List<Member> members = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setMember(Member member) { // 연관관계의 주인은 아니지만 엔티티를 여기서 추가한다.
        member.setTeam(this);
        members.add(member);
    }

}

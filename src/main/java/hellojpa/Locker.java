package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Locker {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToOne(mappedBy = "locker") // 다대일과 같은 원리
    private Member member;

}

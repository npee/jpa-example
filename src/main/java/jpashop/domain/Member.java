package jpashop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SHOP_MEMBER")
@Getter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member") // Order에 있는 member에 의해 관리된다.
    private List<Order> orders = new ArrayList<>();

    private String name;

    @Embedded
    private Address address;

    public void setName(String name) {
        this.name = name;
    }
}

package jpashop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member") // Order에 있는 member에 의해 관리된다.
    private List<Order> orders = new ArrayList<>();

    private String name;
    private String city;
    private String street;
    private String zipcode;

    public void setName(String name) {
        this.name = name;
    }
}

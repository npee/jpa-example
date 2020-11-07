package hellojpa;

import hellojpa.embedded.Address;
import hellojpa.embedded.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // Period
    @Embedded
    private Period period;

    // Address
    @Embedded
    private Address homeAddress;
}


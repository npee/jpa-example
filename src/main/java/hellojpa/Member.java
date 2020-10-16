package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor // JPA는 기본적으로 내부적으로 리플렉션을 쓰기때문에 기본 생성자를 생성해줘야 한다.
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        initialValue = 1, allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "MY_SEQUENCES"
    )
    private Long id;

    @Column(name = "name")
    private String username;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


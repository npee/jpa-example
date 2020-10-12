package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor // JPA는 기본적으로 내부적으로 리플렉션을 쓰기때문에 기본 생성자를 생성해줘야 한다.
@Setter
public class Member {

    @Id
    private Long id;
    private String name;


}


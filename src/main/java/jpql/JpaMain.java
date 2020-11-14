package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);

            em.persist(member);

            em.flush();
            em.clear();

            List<MemberDto> resultList =
                    em.createQuery("select new jpql.MemberDto(m.username, m.age) from Member as m", MemberDto.class)
                    .getResultList();

            MemberDto result = resultList.get(0);
            System.out.println("result.username = " + result.getUsername());
            System.out.println("result.age = " + result.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}

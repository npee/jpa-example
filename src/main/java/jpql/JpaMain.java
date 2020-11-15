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

            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAge(10);

            member1.setTeam(team);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(20);

            member2.setTeam(team);

            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select m from Member as m left join m.team t on t.name = 'team1'";
            List<Member> resultList =
                    em.createQuery(query, Member.class)
                            .getResultList();

            for (Member member : resultList) {
                System.out.println("member1 = " + member);
                System.out.println("member1.getTeam().getName() = " + member.getTeam().getName());
            }

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

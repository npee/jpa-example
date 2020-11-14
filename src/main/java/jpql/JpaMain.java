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
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            List<Member> result =
                    em.createQuery("select m from Member as m", Member.class)
                            .getResultList();

            Team team1 =
                    em.createQuery("select t from Member as m join m.team t", Team.class)
                            .getSingleResult();

            Member findMember = result.get(0);
            findMember.setAge(20);

            team1.setName("TeamAA");

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

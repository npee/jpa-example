package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Team team3 = new Team();
            team3.setName("teamC");
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("A");
            member1.setEmail("A EMAIL");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("B");
            member2.setEmail("B EMAIL");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            // Member m = em.find(Member.class, member1.getId());

            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();


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

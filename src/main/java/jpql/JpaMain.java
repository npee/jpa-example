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

            String query = "select m.username, 'hello', true, 100L from Member as m";
            List<Object[]> results = em.createQuery(query).getResultList();

            for (Object[] result : results) {
                System.out.println("result[0] = " + result[0]);
                System.out.println("result[1] = " + result[1]);
                System.out.println("result[2] = " + result[2]);
                System.out.println("result[3] = " + result[3]);
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

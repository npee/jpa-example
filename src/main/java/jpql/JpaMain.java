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
            member1.setMemberType(MemberType.USER);

            member1.setTeam(team);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(20);

            member2.setTeam(team);

            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select case when m.age <= 15 then '학생요금' " +
                    "when m.age >= 60 then '경로요금' " +
                    "else '일반요금' end " +
                    "from Member m";

            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
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

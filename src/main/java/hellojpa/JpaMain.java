package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
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
            em.persist(team); // TeamA의 pk가 지정되었음

            Member member = new Member();
            member.setUsername("member1");
            // member.setTeamId(team.getId());
            member.setTeam(team); // team을 직접 저장..알아서 FK로 team_id 지정한다.
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            // Long findTeamId = findMember.getTeamId();
            // Team findTeam = em.find(Team.class, findTeamId);
            Team findTeam = findMember.getTeam();

            System.out.println("===============");
            System.out.println("findTeam.getId() = " + findTeam.getId());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

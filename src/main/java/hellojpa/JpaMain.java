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

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team); // team을 직접 저장..알아서 FK로 team_id 지정한다.
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team);
            em.persist(member2);

            em.flush(); // DB에 미리 member들을 넣어둔다.
            em.clear();

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println(findMember.getTeam().getMembers());
            // member1이 속한 팀의 멤버 리스트를 구하려면?
            List<Member> members = findMember.getTeam().getMembers();

            if (members.isEmpty())
                System.out.println("리스트가 비어있습니다.");

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

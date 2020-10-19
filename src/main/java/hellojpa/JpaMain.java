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
            // 양방향 매핑 시 많이 하는 실수
            Team team = new Team();
            team.setName("TeamA");
            // team.getMembers().add(member1); // 여기서 해봤자 반영되지 않는다.
            em.persist(team);

            Member member1 = new Member();
            member1.setTeam(team); // 연관관계의 주인에서 수정이 일어난다.
            member1.setUsername("member1");
            em.persist(member1);

            // team.getMembers().add(member1); // 여기서 해도 문제는 없는데 hibernate에서 반영하지 않는다.

            em.flush(); // DB에 미리 member들을 넣어둔다.
            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

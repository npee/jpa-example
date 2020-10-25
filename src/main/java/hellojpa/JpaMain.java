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

            Member member = new Member();
            member.setUsername("A");

            em.persist(member);

            em.flush();
            em.clear();

            Member getMemberReference = em.getReference(Member.class, member.getId());
            System.out.println("getMemberReference.getClass() = " + getMemberReference.getClass());

            System.out.println("========================================================");

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember.getClass() = " + findMember.getClass());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

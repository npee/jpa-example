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

            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            Member findMember2 = em.getReference(Member.class, member2.getId());
            System.out.println("member2.getClass() = " + findMember2.getClass());
            System.out.println("member2 is equal to findMember(Proxy)? " + member2.equals(findMember2));
            System.out.println("findMember(Proxy) is instance of Member? " + (findMember2 instanceof Member));

            Member findMember1 = em.find(Member.class, member1.getId());
            System.out.println("member1.getClass() = " + findMember1.getClass());
            System.out.println("member1 is equal to findMember? " + member1.equals(findMember1));
            System.out.println("findMember is instance of Member? " + (findMember1 instanceof Member));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

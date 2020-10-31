package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            Member member3 = new Member();
            member3.setUsername("C");

            Member member4 = new Member();
            member4.setUsername("D");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.find(Member.class, member2.getId());
            Member m3 = em.getReference(Member.class, member3.getId());
            Member m4 = em.getReference(Member.class, member4.getId());

            System.out.println("m1 == m2? : " + (m1.getClass() == m2.getClass()));
            System.out.println("m3 == m4? : " + (m3.getClass() == m4.getClass()));
            System.out.println("m1 == m3? : " + (m1.getClass() == m3.getClass()));

            System.out.println("m1 and m3 are instance of Member? : " +
                    (m1 instanceof Member && m2 instanceof Member));


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

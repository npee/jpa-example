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
            List<Member> members = new ArrayList<>();

            for (int i = 0; i < 51; i++) {
                members.add(new Member("SAME_NAME"));
            }
            for (Member member : members) {
                em.persist(member);
            }
            System.out.println("==========");

            Member member52 = new Member("member52");
            em.persist(member52);
            System.out.println("member52.getId() = " + member52.getId());
            System.out.println("==========");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

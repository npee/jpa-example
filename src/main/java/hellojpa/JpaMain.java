package hellojpa;

import hellojpa.embedded.Address;
import hellojpa.embedded.AddressEntity;
import hellojpa.embedded.Period;
import org.hibernate.Hibernate;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            List<Member> list = em.createQuery("select m from MEMBER m where m.username like '%kim%'", Member.class)
                    .getResultList();

            for (Member member : list) {
                System.out.println("member.getUsername() = " + member.getUsername());
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

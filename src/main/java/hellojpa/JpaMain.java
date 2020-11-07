package hellojpa;

import hellojpa.embedded.Address;
import hellojpa.embedded.Period;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("hello");
            Address address = new Address("city", "street", "zipcode");
            member1.setHomeAddress(address);
            member1.setPeriod(new Period());

            Member member2 = new Member();
            member2.setUsername("hello2");
            member2.setHomeAddress(new Address("newCity", address.getStreet(), address.getZipcode()));
            // member2.getHomeAddress().setCity("newCity"); // Compile Error
            member2.setPeriod(new Period());

            em.persist(member1);
            em.persist(member2);

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

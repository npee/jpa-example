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
            Address address1 = new Address("city", "street", "zipcode");
            member1.setHomeAddress(address1);
            member1.setPeriod(new Period());

            Member member2 = new Member();
            member2.setUsername("hello2");
            Address address2 = new Address("city", "street", "zipcode");
            member2.setHomeAddress(address2);
            member2.setPeriod(new Period());

            System.out.println("address1 == address2?: " + (address1 == address2));
            System.out.println("address1 equals address2?: " + (address1.equals(address2)));

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

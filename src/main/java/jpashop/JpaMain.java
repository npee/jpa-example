package jpashop;

import jpashop.domain.*;

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

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            List<Item> items =
                    em.createQuery("select i from Item i where type(i) = Book", Item.class)
                            .getResultList();

            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            // e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

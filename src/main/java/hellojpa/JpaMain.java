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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> from = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(from).where(cb.equal(from.get("username"), "kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();

            for (Member member : resultList) {
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

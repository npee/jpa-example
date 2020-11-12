package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            // 반환 타입이 명확할 때
            TypedQuery<Member> query1 = em.createQuery("select m from Member as m", Member.class);
            TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.id = 1", Member.class);

            // 결과가 하나 이상일 때
            List<Member> resultList = query1.getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            // 결과가 딱 하나일 때
            Member singleResult = query2.getSingleResult();
            System.out.println("singleResult = " + singleResult);


            em.flush();

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

package jpql;

import javax.persistence.*;

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
            TypedQuery<String> query2 = em.createQuery("select m.username from Member as m", String.class);

            // 반환 타입이 명확하지 않을 때
            Query query3 = em.createQuery("select m.username, m.age from Member as m");

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

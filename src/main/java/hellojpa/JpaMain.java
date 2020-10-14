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

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");

            // em.persist(member); // 컬렉션을 다루는 것 처럼 작업하게 되므로 반영하는 코드가 필요없다.\

            // 준영속 상태 만들기 1.
            // 영속성 컨텍스트에서 빼내기
            em.detach(member);

            // 준영속 상태 만들기 2.
            // 영속성 컨텍스트 초기화
            em.clear();

            System.out.println("======================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

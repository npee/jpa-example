package hellojpa;

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
            /*
            // 멤버 추가
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
             */

            /*
            // 멤버 찾기
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
             */

            /*
            // 멤버 삭제(위의 멤버 찾기에서 찾은 멤버를 파라미터로 전달)
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
             */

            /*
            // 멤버 수정(persist() 필요없음. 컬렉션으로 관리하는 것 처럼 설계됨)
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
             */

            /*
            // JPQL (SELECT)
            List<Member> result= em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0).setMaxResults(5)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }
             */

            /*
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
             */

            // 2번 조회
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

            System.out.println("result: " + (findMember1 == findMember2));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

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

            Movie movie = new Movie();
            movie.setDirector("감독");
            movie.setActor("배우");
            movie.setName("영화 제목");
            movie.setPrice(10000);

            Album album = new Album();
            album.setArtist("아티스트");
            album.setName("앨범 제목");
            album.setPrice(15000);

            em.persist(movie);
            em.persist(album);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, 1L);
            System.out.println("findMovie.getName() = " + findMovie.getName());
            System.out.println("findMovie.getDirector() = " + findMovie.getDirector());

            Item item = em.find(Item.class, album.getId());
            Album findAlbum = (Album) item;
            System.out.println("item.getName() = " + findAlbum.getName());
            System.out.println("findAlbum.getArtist() = " + findAlbum.getArtist());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

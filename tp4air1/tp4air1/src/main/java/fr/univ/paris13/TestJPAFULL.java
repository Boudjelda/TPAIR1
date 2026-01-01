package fr.univ.paris13;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import fr.univ.paris13.model.JPA.*;


public class TestJPAFULL {
    public static void main(String[] args) {
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            TypedQuery<Personne> qPers =
                em.createQuery("SELECT p FROM Personne p", Personne.class);
            List<Personne> personnes = qPers.getResultList();
            System.out.println("JPA FULL - PERSONNES : " + personnes.size());

            TypedQuery<Adresse> qAdr =
                em.createQuery("SELECT a FROM Adresse a", Adresse.class);
            List<Adresse> adresses = qAdr.getResultList();
            System.out.println("JPA FULL - ADRESSES : " + adresses.size());

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            JPAUtil.shutdown();
        }
    }
}

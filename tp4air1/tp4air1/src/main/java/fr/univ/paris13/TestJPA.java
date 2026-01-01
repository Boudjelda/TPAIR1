package fr.univ.paris13;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.univ.paris13.hibernate.HibernateUtil;
import fr.univ.paris13.model.JPA.Personne;
import fr.univ.paris13.model.JPA.Adresse;

public class TestJPA{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            // ---------- PERSONNE ----------
            // Criteria
            List<Personne> personnesCrit = session
                    .createCriteria(Personne.class)
                    .list();
            System.out.println("PERSONNES - CRITERIA : " + personnesCrit.size());

            // HQL
            List<Personne> personnesHQL = session
                    .createQuery("FROM Personne")
                    .list();
            System.out.println("PERSONNES - HQL : " + personnesHQL.size());

            // SQL natif
            List<Personne> personnesSQL = session
                    .createSQLQuery("SELECT * FROM personne")
                    .addEntity(Personne.class)
                    .list();
            System.out.println("PERSONNES - SQL : " + personnesSQL.size());


            // ---------- ADRESSE ----------
            // Criteria
            List<Adresse> adressesCrit = session
                    .createCriteria(Adresse.class)
                    .list();
            System.out.println("ADRESSES - CRITERIA : " + adressesCrit.size());

            // HQL
            List<Adresse> adressesHQL = session
                    .createQuery("FROM Adresse")
                    .list();
            System.out.println("ADRESSES - HQL : " + adressesHQL.size());

            // SQL natif
            List<Adresse> adressesSQL = session
                    .createSQLQuery("SELECT * FROM adresse")
                    .addEntity(Adresse.class)
                    .list();
            System.out.println("ADRESSES - SQL : " + adressesSQL.size());


            // ---------- PERSONNES POUR UNE ADRESSE (ex: ville = 'Paris') ----------
            // 1) CRITERIA + relation p.adresse
            Criteria crit = session.createCriteria(Personne.class, "p")
                                   .createAlias("p.adresse", "a")
                                   .add(Restrictions.eq("a.ville", "Paris"));
            List<Personne> personnesCriteriaVille = crit.list();
            System.out.println("CRITERIA - personnes pour ville=Paris : " + personnesCriteriaVille.size());

            // 2) HQL
            List<Personne> personnesHQLVille = session
                    .createQuery("FROM Personne p WHERE p.adresse.ville = :ville")
                    .setString("ville", "Paris")
                    .list();
            System.out.println("HQL - personnes pour ville=Paris : " + personnesHQLVille.size());

            // 3) SQL natif
            List<Personne> personnesSQLVille = session
                    .createSQLQuery(
                        "SELECT p.* FROM personne p " +
                        "JOIN adresse a ON p.id_adresse = a.id " +
                        "WHERE a.ville = :ville")
                    .addEntity(Personne.class)
                    .setString("ville", "DRANCY")
                    .list();
            System.out.println("SQL - personnes pour ville=DRANCY : " + personnesSQLVille.size());

            tx.commit();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }
}

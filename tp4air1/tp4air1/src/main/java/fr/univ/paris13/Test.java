package fr.univ.paris13;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import fr.univ.paris13.hibernate.HibernateUtil;
import fr.univ.paris13.model.Adresse;
import fr.univ.paris13.model.PersonneXML;
//import fr.univ.paris13.model.JPA.*;
public class Test {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            
            List<PersonneXML> personnes = session.createCriteria(PersonneXML.class).list();
            System.out.println("With CRITERIA API : " + personnes.size());

           
            List<PersonneXML> personnesHQL = session.createQuery("FROM Personne").list();
            System.out.println("WITH HQL QUERY : " + personnesHQL.size());

            
            List<PersonneXML> personnesSQL = session.createSQLQuery("select * from personne")
                    .addEntity(PersonneXML.class)
                    .list();
            System.out.println("WITH SQL QUERY : " + personnesSQL.size());

            
            List<Adresse> adresses = session.createCriteria(Adresse.class).list();
            System.out.println("ADRESSES - CRITERIA API : " + adresses.size());

            List<Adresse> adressesHQL = session.createQuery("FROM Adresse").list();
            System.out.println("ADRESSES - HQL QUERY : " + adressesHQL.size());

            List<Adresse> adressesSQL = session.createSQLQuery("select * from adresse")
                    .addEntity(Adresse.class)
                    .list();
            System.out.println("ADRESSES - SQL QUERY : " + adressesSQL.size());

            List<PersonneXML> personnesParAdresse =
            	    session.createQuery("FROM Personne p WHERE p.adresse.ville = :ville")
            	           .setString("ville", "Paris")
            	           .list();

            	System.out.println("PERSONNES pour ville=Paris : " + personnesParAdresse.size());

            
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

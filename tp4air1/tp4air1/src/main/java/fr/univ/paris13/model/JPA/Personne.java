package fr.univ.paris13.model.JPA;

import javax.persistence.*;

@Entity
@Table(name = "personne")
public class Personne {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "tel")
    private String tel;

    @Column(name = "mail")
    private String mail;

    @Column(name = "id_adresse")
    private Integer idAdresse;   // FK brute

    @ManyToOne
    @JoinColumn(name = "id_adresse", insertable = false, updatable = false)
    private Adresse adresse;     // relation objet

    public Personne() {}

    // getters / setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public Integer getIdAdresse() { return idAdresse; }
    public void setIdAdresse(Integer idAdresse) { this.idAdresse = idAdresse; }

    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
}

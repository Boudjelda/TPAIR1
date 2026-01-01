package fr.univ.paris13.model;

public class PersonneXML {
    private Integer id;
    private String nom;
    private String prenom;
    private String tel;
    private String mail;
    private Integer idAdresse;
    
    private Adresse adresse;     
    
    


    // Constructeurs
    public PersonneXML() {}
    public PersonneXML(Integer id, String nom, String prenom, String tel, String mail, Integer idAdresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
        this.idAdresse = idAdresse;
    }

    // Getters/Setters OBLIGATOIRES
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
    
    public Adresse getAdresse() {
        return adresse;
    }
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}

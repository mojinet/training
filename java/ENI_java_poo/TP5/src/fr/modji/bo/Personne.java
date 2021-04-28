package fr.modji.bo;

public class Personne {
    // Attribute
    protected String nom, prenom, telephone;
    protected Adresse adresse;

    // Constructor
    public Personne(String nom, String prenom, String telephone, Adresse adresse){
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
    }
    public Personne(String nom, String prenom, String telephone){
        this(nom,prenom,telephone,null);
    }

    // Getter
    public String getNumeroDeTelephone(){ return this.telephone; }
    public String getNom() { return this.nom; }
    public String getPrenom() { return this.prenom; }
    public Adresse getAdresse() {return this.adresse; }
    // Setter
    public void setNumeroDeTelephone(String num){ this.telephone = num; }

    //Method
    public void afficher(){
        // affiche l'adresse si elle existe
        if ( this.getAdresse() != null ){ this.getAdresse().afficher(); }
    }

}

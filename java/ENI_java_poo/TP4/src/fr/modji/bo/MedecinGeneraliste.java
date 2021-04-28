package fr.modji.bo;

import java.util.Locale;

public class MedecinGeneraliste {

    // Attribute
    private String nom, prenom, telephone;
    private static int tarif = 25;
    private Adresse adresse;
    private Creneau[] creneau = new Creneau[15];
    private int compteurCreneau = 0;

    // Constructor
    public MedecinGeneraliste(String nom, String prenom, String telephone, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
    }
    public MedecinGeneraliste(String nom, String prenom, String telephone) {
        this(nom, prenom,telephone,null);
    }

    // Method
    public String getNumeroDeTelephone(){ return this.telephone; }
    public String getNom() { return this.nom; }
    public String getPrenom() { return this.prenom; }
    public Adresse getAdresse() {return this.adresse; }
    public void setNumeroDeTelephone(String num){ this.telephone = num; }
    public static int getTarif() { return tarif; }
    public static void setTarif(int tarif) { MedecinGeneraliste.tarif = tarif; }

    public void ajouterCreneau(Creneau creneau){
        if (this.compteurCreneau < this.creneau.length){
            this.creneau[this.compteurCreneau] = creneau;
            this.compteurCreneau++;
        }else{
            System.out.println("Impossible d'ajouter un creneau (15/15)");
        }
    }

    public void afficher(){
        // affiche l'adresse si elle existe
        if ( this.getAdresse() != null ){ this.getAdresse().afficher(); }
        // Affiche les informations du médecin
        System.out.printf( "%s %s %nTéléphone : %s %nTarif : %d € %n", getNom().toUpperCase(), getPrenom(), getNumeroDeTelephone(), MedecinGeneraliste.getTarif());
        // Affiche ses creneaux horaires
        for (int i = 0; i < this.compteurCreneau; i++) {
            this.creneau[i].afficher();
            System.out.println();
        }
    }
}

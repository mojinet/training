package fr.modji.bo;

import java.util.Locale;

public class MedecinGeneraliste {

    // Attribute
    private String nom, prenom, telephone;
    private static int tarif;

    // Constructor
    public MedecinGeneraliste(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    // Method
    public String getNumeroDeTelephone(){ return this.telephone; }
    public String getNom() { return this.nom; }
    public String getPrenom() { return this.prenom; }
    public void setNumeroDeTelephone(String num){ this.telephone = num; }
    public static int getTarif() { return tarif; }
    public static void setTarif(int tarif) { MedecinGeneraliste.tarif = tarif; }

    public void afficher(){
        System.out.printf( "%s %s %n Téléphone : %s %n Tarif : %d € %n", getNom().toUpperCase(), getPrenom(), getNumeroDeTelephone(), MedecinGeneraliste.getTarif());
    }
}

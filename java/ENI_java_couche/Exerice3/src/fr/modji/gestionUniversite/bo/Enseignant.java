package fr.modji.gestionUniversite.bo;

public abstract class Enseignant {
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNbHeure() {
        return nbHeure;
    }

    public void setNbHeure(int nbHeure) {
        this.nbHeure = nbHeure;
    }

    protected String nom;
    protected String prenom;

    public Enseignant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    protected int nbHeure;
    protected static final int TAUX_CHARGE = 2;

    public abstract double getCout();
}

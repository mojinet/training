package fr.modji.gestionUniversite.bo;

public class Vacataire extends Enseignant{
    private static final int HEURE = 40;

    public Vacataire(String nom, String prenom) {
        super(nom, prenom);
    }

    @Override
    public double getCout() {
        int heure = getNbHeure();
        int salaire;

        salaire = heure * Vacataire.HEURE;

        return salaire;
    }
}

package fr.modji.gestionUniversite.bo;

public class Etudiant extends Enseignant{
    private static final int HEURE = 40;
    private static final int MAX_HEURE = 96;

    public Etudiant(String nom, String prenom) {
        super(nom, prenom);
    }

    @Override
    public double getCout() {
        int heure = getNbHeure();
        int salaire;

        if (heure > Etudiant.MAX_HEURE){
            salaire = Etudiant.MAX_HEURE * Etudiant.HEURE;
        }else{
            salaire = heure * Etudiant.HEURE;;
        }
        return salaire;
    }
}

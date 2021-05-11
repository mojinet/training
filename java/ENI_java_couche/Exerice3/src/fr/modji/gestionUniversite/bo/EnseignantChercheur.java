package fr.modji.gestionUniversite.bo;

public class EnseignantChercheur extends Enseignant{
    private static final int SALAIRE_FIXE = 2000;
    private static final int HEURE_SUP = 40;

    public EnseignantChercheur(String nom, String prenom) {
        super(nom, prenom);
    }

    @Override
    public double getCout() {
        int heure = getNbHeure();
        int salaire;

        if (heure > 192){
            salaire = (heure - 192) * EnseignantChercheur.HEURE_SUP + EnseignantChercheur.SALAIRE_FIXE;
        }else{
            salaire = EnseignantChercheur.SALAIRE_FIXE;
        }
        return salaire * Enseignant.TAUX_CHARGE;
    }

}

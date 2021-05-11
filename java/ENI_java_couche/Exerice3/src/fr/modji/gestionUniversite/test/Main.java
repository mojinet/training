package fr.modji.gestionUniversite.test;

import fr.modji.gestionUniversite.bo.Enseignant;
import fr.modji.gestionUniversite.bo.EnseignantChercheur;
import fr.modji.gestionUniversite.bo.Etudiant;
import fr.modji.gestionUniversite.bo.Vacataire;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Enseignant> personnels = new ArrayList<>();

        EnseignantChercheur ec = new EnseignantChercheur("bob","dylan");
        ec.setNbHeure(200);
        Vacataire c = new Vacataire("Brigite","toutcourt");
        c.setNbHeure(200);
        Etudiant e = new Etudiant("Henrie","Ford");
        e.setNbHeure(200);

        personnels.add(ec);
        personnels.add(c);
        personnels.add(e);

        for (Enseignant employe : personnels) {
            System.out.println(employe.getClass().getSimpleName() + " coute " + employe.getCout() + " € pour " + employe.getNbHeure() + " heure" );
        }

    }
}

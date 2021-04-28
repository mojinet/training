package fr.modji.location;

import java.time.LocalDate;

public class Gyroroue extends Vehicule {
    private int autonomie;

    public Gyroroue(String marque, String modele, LocalDate dateAchat, int autonomie){
        super(marque,modele,dateAchat);
        this.autonomie = autonomie;
    }

    public int getAutonomie() {
        return autonomie;
    }

    @Override
    public String toString() {
        return String.format("Velo %s %s (année %d) %d km d'autonomie", this.getMarque(), this.getModele(), this.getDateAchat(), this.getAutonomie());
    }
}

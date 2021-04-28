package fr.modji.location;

import java.time.LocalDate;

public class Gyropode extends Vehicule{
    private int autonomie;
    private int tailleMin;

    public Gyropode(String marque, String modele, LocalDate dateAchat, int autonomie, int tailleMin){
        super(marque,modele,dateAchat);
        this.autonomie = autonomie;
        this.tailleMin = tailleMin;
    }

    public int getAutonomie() {
        return autonomie;
    }

    public int getTailleMin(){
        return tailleMin;
    }

    @Override
    public String toString() {
        return String.format("Velo %s %s (année %d) %d km d'autonomie [%d min]", this.getMarque(), this.getModele(), this.getDateAchat(), this.getAutonomie(), this.getTailleMin());
    }
}
